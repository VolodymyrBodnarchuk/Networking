public class CustomJackson {
    public static void main(String[] args){
        var json = "{\n" +
                " \"firstName\" : \"Volodymyr\",\n "+
                " \"lastName\" : \"Bodnarchuk\", \n" +
                " \"email\" : \"bodnarchuk@gmail.com\" \n" +
                "}";

        var user = jsonToObj(json, User.class);
        System.out.println(user);

    }

    private static <T> T jsonToObj(String json, Class<User> userClass) {
        return null;
    }

    static class User{
        private String firstname;
        private String lastname;
        private String email;
    }
}
