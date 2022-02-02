package connection;

import java.sql.Connection;
import java.sql.DriverManager;
//criando padr�o Singleton(permite apenas uma instancia da classe)
//para acessar o banco de dados(j� que estamos usando JDBC, fazer na mao)
public class SingleConnectionBanco {

	//static->�nico(apenas nessa classe)
	private static String banco="jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user="postgres";
	private static String senha="admin";//definida na instala��o
	private static Connection connection=null;//iniciar null

	//com o static n�o precisa criar um objeto para utilizar a variavel ou m�todo, pode fazer: SingleConnectionBanco.getConnection(); que 
	//normalmente seria SingleConnectionBanco conexaoBanco=new SingleConnectionBanco(); conexaoBanco.getConnection();
	//retorna o objeto Connection
	public static Connection getConnection() {
		return connection;
	}
	
	//de qualquer lugar que chamar o SingleConnectionBanco vai chamar o conectar()
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		conectar();
	}
	
	private static void conectar() {
		try {
			//caso n�o tenha levantado a conex�o ainda
			if(connection==null) {
				Class.forName("org.postgresql.Driver");//driver do postgres
				//metodo do objeto DriverManager
				connection=DriverManager.getConnection(banco, user, senha);//banco=url do banco
				connection.setAutoCommit(false);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
