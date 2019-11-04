package studio.lineage2.authserver.xmlrpclogin;


public interface ILogin
{
	String reg(String login, String password);

	String change(String login, String password);
}
