package ec.gob.ceaaces.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@SuppressWarnings("unchecked")
public class JBOSS7EJB3ContextHelper {
    private static Context context;
    private final Map<String, Object> services = new HashMap<String, Object>();

    public JBOSS7EJB3ContextHelper(Map<String, String> servicios) {
        super();

        try {
            @SuppressWarnings("rawtypes")
            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.URL_PKG_PREFIXES,
                    "org.jboss.ejb.client.naming");
            context = new InitialContext(jndiProperties);
            for (String serviceName : servicios.keySet()) { //
                this.services.put(serviceName,
                        context.lookup(servicios.get(serviceName)));
            }
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Object getServicioPorNombre(String nombreServicio) {
        return this.services.get(nombreServicio);
    }
}
