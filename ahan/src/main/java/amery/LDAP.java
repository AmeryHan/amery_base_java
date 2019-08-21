package amery;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

public class LDAP {

    private static LdapTemplate ldapTemplate;

    @BeforeClass
    public static void setBefore() {

        ldapTemplate = new LdapTemplate();

        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://xxxx.hp.com:xxxx/");
        contextSource.setBase("ou=People,o=hp.com");
        contextSource.setAnonymousReadOnly(true);
        contextSource.setCacheEnvironmentProperties(false);

        ldapTemplate.setContextSource(contextSource);
    }

    @Test
    @SuppressWarnings("unused")
    public void testSearchByAbstractContextMapper() {

        EqualsFilter f = new EqualsFilter("mail", "xxxxxxxxxx@hp.com");
        try {
            List<?> result = ldapTemplate.search(DistinguishedName.EMPTY_PATH,
                    f.toString(), new AbstractContextMapper() {
                        protected Object doMapFromContext(
                                DirContextOperations ctx) {
                            String mail = ctx.getStringAttribute("mail");
                            String managerUid = ctx
                                    .getStringAttribute("hpCrossCompanyManager");
                            String userId = ctx.getStringAttribute("uid");
                            String hpStatus = ctx
                                    .getStringAttribute("hpStatus");
                            String givenName = ctx
                                    .getStringAttribute("givenName");
                            String employeeNumber = ctx
                                    .getStringAttribute("employeeNumber");
                            String userRegionCode = ctx
                                    .getStringAttribute("hpPayrollCountryCode");
                            String managerEmployeeNumber = ctx.getStringAttribute("managerEmployeeNumber");

                            return null;
                        }
                    });
            if (result.size() != 1) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("unused")
    public void testSearch() {

        EqualsFilter f = new EqualsFilter("mail", "xxxxxxxxx@xx.com");
        try {
            List<?> result = ldapTemplate.search(DistinguishedName.EMPTY_PATH,
                    f.toString(), new AttributesMapper() {

                        public Object mapFromAttributes(Attributes attributes)
                                throws NamingException {

                            System.out.println(attributes);
                            return null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
