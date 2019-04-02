package Validator;
import Exceptions.ValidatorException;
import Domain.Student;
import java.util.regex.*;

public class StudentValidator implements IValidator<Student> {

    public void validate(Student s) throws ValidatorException {
        String errors="";

        Pattern idp = Pattern.compile("[a-zA-Z0-9]+");
        Pattern groupp = Pattern.compile("0|([1-9][0-9]*)");
        Pattern namep = Pattern.compile("[A-Za-z-]+ [A-Za-z-]+");
        Pattern emailp = Pattern.compile(".+@.+\\..+");

        if(!idp.matcher(s.getId()).matches()){
            //throw new ValidatorException("Id invalid\n");
            errors+="Id invalid\n";
        }
        if(!namep.matcher(s.getNume()).matches()){
            //throw new ValidatorException("Nume invalid\n");
            errors+="Nume invalid\n";
        }
        if(!namep.matcher(s.getIndrumator()).matches()){
            //throw new ValidatorException("Nume invalid\n");
            errors+="Indrumator invalid\n";
        }
        if((!groupp.matcher(Integer.toString(s.getGrupa())).matches())){
            //throw new ValidatorException("Grupa invalida\n");
            errors+="Grupa invalid\n";
        }

        if(!emailp.matcher(s.getEmail()).matches()){
            //throw new ValidatorException("Email invalid\n");
            errors+="Email invalid\n";
        }
        if (errors.length()!=0){
            throw  new ValidatorException(errors);
        }
    }
}