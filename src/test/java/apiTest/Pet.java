package apiTest;

public class Pet {
        public int id;
        public String name;
        public String photoUrls;
        public String status;

        static class Category {

                Category(String category_id, String category_name) {
                        this.category_id = category_id;
                        this.category_name = category_name;
                }

                public String category_id;

                public String category_name;
        }

        static class Tags {

                Tags(int id, String name) {
                        this.id = id;
                        this.name = name;
                }

                public int id;
                public String name;
        }
}