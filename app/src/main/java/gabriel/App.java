package gabriel;

public class App {
    public static void main(String[] args) {

        System.out.println(Long.MIN_VALUE);

        Person person = new Person.Builder().withUsername("gabriel").build();
        String lala = person.getUsername();

        System.out.println(lala);

        lala = "seilaqualquercoisa";

        System.out.println(lala);

        System.out.println(person.getUsername());

    }

    static class Person {

        public static class Builder {
            private String username;

            public Builder() {
            }

            public Builder withUsername(String username) {
                this.username = username;
                return this;
            }

            public Person build() {
                return new Person(this);
            }

        }

        private String username;

        private Person(Builder builder) {
            this.username = builder.username;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Person [username=" + username + "]";
        }

    }

}
