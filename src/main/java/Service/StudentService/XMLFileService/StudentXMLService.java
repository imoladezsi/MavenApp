package Service.StudentService.XMLFileService;

import Domain.Student;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import com.sun.org.apache.xpath.internal.operations.Number;

public class StudentXMLService extends AbstractXMLService<String,Student> {
    private StudentXMLRepo xmlrepo;

    public StudentXMLService(StudentXMLRepo xmlrepo)  {
        super(xmlrepo);
    }

    @Override
    protected Student extractEntity(String[] params) throws ValidatorException {
        int grupa=0;
        try {
            grupa = Integer.parseInt(params[2]);
        }catch (NumberFormatException e){
            throw new ValidatorException("Student group must be an integer number");
        }
        return new Student(params[0],params[1],grupa,params[3],params[4]);
    }

}