package Service.StudentService;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import Service.StudentService.XMLFileService.StudentXMLService;
import Validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddStudent {


    public void shouldPass(String[] params)
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        try{
            stsrv.add(params);
            assertNotNull(stsrv.findOne(params[0]));
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    public void shouldFail(String[] params)
    {
        boolean gotThere= false;
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;
        }
        assertTrue(gotThere);

    }

    //Group tests
    @Test
    // The point of this test case is to pass
    // This test case will be used later when analyzing a specific value
    // For all the values that we don't analyze, we will use these values as a default
    public void shouldPass_All_DefaultsToUse()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);

    }
    @Test
    public void shouldFail_Group_EmptyString()
    {
        String[] params={"daea1111","Nume Prenume","","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }


    @Test
    public void shouldFail_Group_NotConvertibleToNumber()
    {
        String[] params={"daea1111","Nume Prenume","NAN","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    /*
    @Test
    public void shouldPass_Group_ConvertibleToNumber()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }*/

    @Test
    public void shouldFail_Group_ConvertibleToNegative()
    {
        String[] params={"daea1111","Nume Prenume","-933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldPass_Group_ConvertibleTo0()
    {
        String[] params={"daea1111","Nume Prenume","0","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    @Test
    public void shouldPass_Group_Convertible3Digits()
    {
        String[] params={"daea1111","Nume Prenume","100","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    @Test
    public void shouldPass_Group_ConvertibleLessThan3Digits()
    {
        String[] params={"daea1111","Nume Prenume","10","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }


    @Test
    public void shouldPass_Group_ConvertibleMoreThan3Digits()
    {
        String[] params={"daea1111","Nume Prenume","1000","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    //Id tests

    @Test
    public void shouldPass_Id_ConvertibleAlphaNumeric()
    {
        String[] params={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    @Test
    public void shouldPass_Id_ConvertibleOnlyNaNCharacters()
    {
        String[] params={"daea","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }
    @Test
    public void shouldPass_Id_ConvertibleNumberCharacters()
    {
        String[] params={"1111aaaa","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);

    }

    @Test
    public void shouldPass_Id_LongString()
    {
        String[] params={"zzzzzzzzzzzzzzzzzzzzzzzzzzz","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    @Test
    public void shouldFail_Id_SpecialCharacters()
    {
        String[] params={"daea1111%","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldFail_Id_Empty()
    {
        String[] params={"","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);

    }

    @Test
    public void shouldFail_Id_Space()
    {
        String[] params={" ","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);

    }

    @Test
    public void shouldFail_Id_NoUnique()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        try{
            stsrv.add(params);
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            assertTrue(true);

        }

    }

    //Name tests
    @Test
    public void shouldPass_Name_StringsWithSpace()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);
    }

    @Test
    public void shouldFail_Name_NoSpace()
    {
        String[] params={"daea1111","NumePrenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldFail_Name_Space()
    {
        String[] params={"daea1111"," ","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldFail_Name_Empty()
    {
        String[] params={"","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldFail_Name_WithDigit()
    {
        String[] params={"daea1111","Nume Prenume4","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);

    }
    //Professor name tests

    @Test
    public void shouldFail_ProfessorName_NoSpace()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","NumePrenume"};
        shouldFail(params);
    }
    @Test
    public void shouldPass_ProfessorName_StringsWithSpace()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);

    }

    @Test
    public void shouldFail_ProfessorName_Space()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro"," "};
        shouldFail(params);
    }

    @Test
    public void shouldFail_ProfessorName_Empty()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro",""};
        shouldFail(params);
    }

    @Test
    public void shouldFail_ProfessorName_WithDigit()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume4"};
        shouldFail(params);
    }

    //e-mail tests
    @Test
    public void shouldPass_Email_CorrectFormat()
    {
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPass(params);

    }
    @Test
    public void shouldFail_Email_JustAt()
    {
        String[] params={"daea1111","Nume Prenume","933","abc@abc","Nume Prenume"};
        shouldFail(params);

    }
    @Test
    public void shouldFail_Email_JustDot()
    {
        String[] params={"daea1111","Nume Prenume","933","abc.abc","Nume Prenume"};
        shouldFail(params);

    }

    @Test
    public void shouldFail_Email_JustRandomString()
    {
        String[] params={"daea1111","Nume Prenume","933","qweryijhgfsa","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shouldFail_Email_Space()
    {
        String[] params={"daea1111","Nume Prenume","933"," ","Nume Prenume"};
        shouldFail(params);

    }

    @Test
    public void shouldFail_Email_EmptyString()
    {
        String[] params={"daea1111","Nume Prenume","933","","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shoulFail_Id_Null(){
        String[] params={null,"Nume Prenume","933","daea1111@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shoulFail_Name_Null(){
        String[] params={"daea1111",null,"933","daea1111@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shoulFail_Group_Null(){
        String[] params={"daea1111","Nume Prenume",null,"daea@scs.ubbcluj.ro","Nume Prenume"};
        shouldFail(params);
    }

    @Test
    public void shoulFail_Prof_Null(){
        String[] params={"daea1111","Nume Prenume","933","email@scs.ubbcluj.ro",null};
        shouldFail(params);
    }

    @Test
    public void shoulFail_Email_Null(){
        String[] params={"daea1111","Nume Prenume","933",null,"Nume Prenume"};
        shouldFail(params);
    }


}