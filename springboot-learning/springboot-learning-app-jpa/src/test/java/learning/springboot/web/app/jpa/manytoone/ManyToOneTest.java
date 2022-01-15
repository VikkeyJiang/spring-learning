package learning.springboot.web.app.jpa.manytoone;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest
class ManyToOneTest {


    @Nested
    @Disabled
    class CascadeMergeTest {

        @Autowired
        private PersonRepository personRepository;

        @Autowired
        private PhoneRepository phoneRepository;

        /**
         * Merge情况下，如果表中没有对应对象，则会报错
         * <p>
         */
        @Test
        public void testCascadeMerge() {

            // 表里面没有数据时抛出异常
            assertThrows(InvalidDataAccessApiUsageException.class, () -> {
                // 此处抛出异常,数据不会保存
                phoneRepository.saveAndFlush(new Phone(new Person("BadPerson"), "BasPhone"));
            });

            // 表里面有数据时
            Person firstPerson = personRepository.saveAndFlush(new Person("FirstMergePerson"));
            Person secondPerson = personRepository.saveAndFlush(new Person("SecondMergePerson"));
            Phone phone1 = phoneRepository.saveAndFlush(new Phone(firstPerson, "phone1"));
            phone1.setPhoneNum("phone10");
            phone1.getPerson().setName("FirstMergePerson_1");
            phone1 = phoneRepository.saveAndFlush(phone1);

            assertEquals("phone10", phone1.getPhoneNum());
            assertEquals("FirstMergePerson_1", phone1.getPerson().getName());

            phone1.setPerson(secondPerson);
            phone1 = phoneRepository.saveAndFlush(phone1);


        }
    }

    @Nested
    class CascadePersistTest {

        @Autowired
        private PersonRepository personRepository;

        @Autowired
        private PhoneRepository phoneRepository;

        @Test
        public void persistWithExistData() {
            // 关联对象在表里面已经存在时，则抛出异常
            Person badPersistPerson = new Person("BadPersistPerson");
            Person savedBadPersistPerson = personRepository.saveAndFlush(badPersistPerson);
            Phone badPersistPhone = new Phone(savedBadPersistPerson, "BadPersistPhone");
            assertThrows(Exception.class, () -> phoneRepository.saveAndFlush(badPersistPhone));
        }

        /**
         * Persist情况下，如果表中没有对应对象，则会添加，如果有，则报错
         */
        @Test
        public void persistWithNonExistData() {
            // 表里面没有数据时，正常保存
            Phone phone = new Phone(new Person("PersistPerson"), "PersistPhone");
            Phone savedPhone = phoneRepository.saveAndFlush(phone);
            assertNotNull(savedPhone.getId());
            assertNotNull(savedPhone.getPerson().getId());
        }

        /**
         * Persist情况下，如果表中没有对应对象，则会添加，如果有，则报错
         */
        @Test
        public void persistWithUpdate() {

            // 对于保存的数据，执行更新操作，保存时，只会更新当前对象的内容，不会更新关联对象的内容
            Phone phone = new Phone(new Person("PersistPerson"), "PersistPhone");
            Phone savedPhone = phoneRepository.saveAndFlush(phone);
            // 修改自身属性
            savedPhone.setPhoneNum("NewPersistPhone");
            // 修改关联对象属性
            savedPhone.getPerson().setName("NewPersistPerson");
            savedPhone = phoneRepository.saveAndFlush(savedPhone);

            assertEquals(savedPhone.getPhoneNum(), "NewPersistPhone");
            assertEquals(savedPhone.getPerson().getName(), "PersistPerson");

        }

        /**
         * Persist情况下，如果表中没有对应对象，则会添加，如果有，则报错
         */
        @Test
        public void persistWithDelete() {

            // 对于保存的数据，执行更新操作，保存时，只会更新当前对象的内容，不会更新关联对象的内容
            Phone phone = new Phone(new Person("PersistPerson"), "PersistPhone");
            Phone savedPhone = phoneRepository.saveAndFlush(phone);
            phoneRepository.delete(savedPhone);
            assertTrue(personRepository.findById(savedPhone.getPerson().getId()).isPresent());
        }
    }


}