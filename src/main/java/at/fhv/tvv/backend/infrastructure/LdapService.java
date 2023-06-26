package at.fhv.tvv.backend.infrastructure;

import at.fhv.tvv.backend.interfaces.LdapServiceInt;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

@Stateless
public class LdapService implements LdapServiceInt {

    @Override
    public String customerInLdap(String username, String password) {

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.40.167:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=customers,dc=ad,dc=team1, dc=com");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {

            // Create the initial context
            DirContext ctx = new InitialDirContext(env);

            // Set up the search controls
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attributes = {"cn", "userPassword", "description"};
            searchControls.setReturningAttributes(attributes);

            // Search for entries
            String filter = "(objectClass=organizationalPerson)";
            NamingEnumeration<SearchResult> result = ctx.search("cn=" + username + ",ou=customers,dc=ad,dc=team1, dc=com", filter, searchControls);

            // Print out the result
            while (result.hasMore()) {
                SearchResult searchResult = result.next();
                System.out.println(searchResult.getNameInNamespace());
                Attributes attrs = searchResult.getAttributes();
                Attribute cnAttr = attrs.get("cn");
                Attribute sn = attrs.get("sn");
                System.out.println(cnAttr);
                String cn = (String) cnAttr.get();
                Attribute pw = attrs.get("userPassword");
                Attribute desc = attrs.get("description");
                System.out.println(pw);
                System.out.println(" cn " + cn);
                System.out.printf(" pw " + pw);
                System.out.println("Description " + desc);
                return desc.get().toString();
            }

            ctx.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



        return "FALSE";

    }
}
