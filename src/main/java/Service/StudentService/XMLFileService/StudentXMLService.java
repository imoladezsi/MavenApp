package Service.StudentService.XMLFileService;

import Domain.Student;
import Repository.XMLFileRepository.StudentXMLRepo;
import com.sun.org.apache.xpath.internal.operations.Number;

public class StudentXMLService extends AbstractXMLService<String,Student> {
    private StudentXMLRepo xmlrepo;

    public StudentXMLService(StudentXMLRepo xmlrepo)  {
        super(xmlrepo);
    }

    @Override
    protected Student extractEntity(String[] params) throws NumberFormatException {
        int grupa=0;

        grupa=Integer.parseInt(params[2]);
        return new Student(params[0],params[1],grupa,params[3],params[4]);
    }

}