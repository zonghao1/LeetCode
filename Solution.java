public class Solution {

    public static void main(String[] args) {
        Resource s = Resource.getResrouceObject();
        System.out.println(s.s);

    }


}

class Resource {
    String s;
    private static Resource resource = null;

    private Resource() {
        s = "New Resoruce";
    }

    static Resource getResrouceObject() {
        if (resource != null) {
            return resource;
        }
        Resource singleton = new Resource();
        return singleton;
    }

}