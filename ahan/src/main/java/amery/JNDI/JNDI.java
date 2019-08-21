package amery.JNDI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JNDI {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Hashtable env = new Hashtable();
        //env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory"); //jboss
        //env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "file:/");
        env.put(Context.OBJECT_FACTORIES, "foo.bar.ObjFactory");
        env.put("foo", "bar");

        // Call the constructor
        Context ctx;
        try {
            ctx = new InitialContext(env);
            System.out.println(ctx.getEnvironment());
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // See what environment properties you have

    }

}
