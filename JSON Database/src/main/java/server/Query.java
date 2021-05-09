package server;

public class Query {

    public String query(Criteria criteria) {
        try {
            Database database = Database.getInstance();
            switch (criteria.type) {
                case "set":
                    return database.set(getKeys(criteria), criteria.value);
                case "get":
                    return database.get(getKeys(criteria));
                case "delete":
                    return database.delete(getKeys(criteria));
                case "exit":
                    return Response.isStatusOK();
                default:
                    throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return Response.isRuntimeError();
        }
    }

    private String[] getKeys(Criteria criteria) {
        return criteria.key == null ? new String[0] : criteria.key.split(",");
    }
}
