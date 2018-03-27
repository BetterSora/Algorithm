package cn.njupt.dog_cat_queue_22;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列问题
 * @author Qin
 */
public class DogCatQueue {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetEnterQueue {
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public long getCount() {
            return count;
        }

        public Pet getPet() {
            return pet;
        }
    }

    public static class DogCatQueueImpl {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public DogCatQueueImpl() {
            this.dogQ = new LinkedList<>();
            this.catQ = new LinkedList<>();
            this.count = 0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("dog")) {
                dogQ.add(new PetEnterQueue(pet, count++));
            } else if (pet.getPetType().equals("cat")) {
                catQ.add(new PetEnterQueue(pet, count++));
            } else {
                throw new RuntimeException("not cat or dog!");
            }
        }

        public Pet pollAll() {
            if (!isCatEmpty() && !isDogEmpty()) {
                return dogQ.peek().getCount() > catQ.peek().getCount() ? dogQ.poll().getPet() :catQ.poll().getPet();
            } else if (!isDogEmpty()) {
                return dogQ.poll().getPet();
            } else if (!isCatEmpty()) {
                return catQ.poll().getPet();
            } else {
                throw new RuntimeException("Queue is empty");
            }
        }

        public Cat pollCat() {
            if (isCatEmpty()) {
                throw new RuntimeException("no cat");
            }

            return ((Cat) catQ.poll().getPet());
        }

        public Dog pollDog() {
            if (isCatEmpty()) {
                throw new RuntimeException("no dog");
            }

            return ((Dog) dogQ.poll().getPet());
        }

        public boolean isDogEmpty() {
            return dogQ.isEmpty();
        }

        public boolean isCatEmpty() {
            return catQ.isEmpty();
        }

        public boolean isEmpty() {
            return dogQ.isEmpty() && catQ.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatQueueImpl test = new DogCatQueueImpl();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }
    }
}
