import java.util.Properties;

public class TestSystemClass
	{	public static void main(String[] args)
			{	Properties ps ;
				ps=System.getProperties();
				System.out.println("Java Runtime Environment Version   :"+ps.getProperty("java.version"));
				System.out.println("Java Runtime Environment Vendor    :"+ps.getProperty("java.vendor"));
				System.out.println("Java Runtime Environment Vendor url:"+ps.getProperty("java.version.url"));
				
				System.out.println("Java Home Directory                :"+ps.getProperty("java.home"));
				
				System.out.println("Java VM Version                    :"+ps.getProperty("java.vm.version"));
				System.out.println("Java VM Vendor                     :"+ps.getProperty("java.vm.vendor"));
				System.out.println("Java VM Name                       :"+ps.getProperty("java.vm.name"));
				
				System.out.println("Java Class Version                 :"+ps.getProperty("java.class.version"));
				System.out.println("Java Class Path                    :"+ps.getProperty("java.class.path"));
				System.out.println("Java Library Path                  :"+ps.getProperty("java.library.path"));
				
				System.out.println("Java Compiler                      :"+ps.getProperty("java.compiler"));
				
				System.out.println("Operating System Name              :"+ps.getProperty("os.name"));
				System.out.println("Operating System Architecture      :"+ps.getProperty("os.arch"));
				System.out.println("Operating System Version           :"+ps.getProperty("os.version"));
				
				System.out.println("File Separator                     :"+ps.getProperty("file.separator"));
				System.out.println("Path Separator                     :"+ps.getProperty("path.separator"));
				System.out.println("Line Separator                     :"+ps.getProperty("line.separator"));
				
				System.out.println("User Name                          :"+ps.getProperty("user.name"));
				System.out.println("User Home                          :"+ps.getProperty("user.home"));
				System.out.println("User Directory                     :"+ps.getProperty("user.dir"));
			}
	}
