import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
    public  static void main(String[] args)
    {
        try {
            Connection conexion=Conector.getConnectio();
            conexion = Conector.getConnectio();
            Statement statement = conexion.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM persona");
            while (resultado.next())
            {
                String rut = resultado.getString("rut");
                String nombre = resultado.getString("nombre");
                String apellidoMaterno = resultado.getString("apellido-materno");
                String apellidoPaterno = resultado.getString("apellido-paterno");
                String numeroTelefonico = resultado.getString("numero-telefonico");

                System.out.println(rut+" "+nombre+" "+apellidoPaterno+" "+apellidoMaterno+" "+numeroTelefonico);
            }

            System.out.println("hola");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
