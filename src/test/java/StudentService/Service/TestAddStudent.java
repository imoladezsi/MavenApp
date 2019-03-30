package StudentService.Service;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.StudentXMLRepo;
import StudentService.Service.XMLFileService.StudentXMLService;
import Validator.StudentValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddStudent {

    //Group tests
    @Test
    // The point of this test case is to pass
    // This test case will be used later when analyzing a specific value
    // For all the values that we don't analyze, we will use these values as a default
    public void shouldPass_All_DefaultsToUse()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","name","933","email@scs.ubbcluj.ro","AVescan"};
             try{
            stsrv.add(params);
            assertNotNull(stsrv.findOne("1"));
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();
        }

    }
    @Test
    public void shouldFail_Group_EmptyString()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","name","","email@scs.ubbcluj.ro","AVescan"};
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            assertTrue(true);

        }

    }


    @Test
    public void shouldFail_Group_NotConvertibleToNumber()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","NotNumber","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Group_ConvertibleToNumber()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","933","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Group_ConvertibleToNegative()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","-933","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Group_ConvertibleTo0()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","0","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Group_Convertible3Digits()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","101","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Group_ConvertibleLessThan3Digits()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","10","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }


    @Test
    public void shouldPass_Group_ConvertibleMoreThan3Digits()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    //Id tests

    @Test
    public void shouldPass_Id_ConvertibleAlphaNumeric()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"diie1233","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Id_ConvertibleOnlyNaNCharacters()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"abc","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }
    @Test
    public void shouldPass_Id_ConvertibleNumberCharacters()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"123","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Id_LongString()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"poiuytrewqlkjhgfdsamnbvcxz908765432qoiuytrewmnbvcxzjhgfds876543","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Id_SpecialCharacters()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"@#$%^&","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Id_Empty()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

/*
    @Test
    public void shouldFail_Id_Space()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={" ","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Id_NoUnique()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"2","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    //Name tests
    @Test
    public void shouldPass_Name_StringsWithSpace()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc def","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldPass_Name_NoSpace()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"abc","abc def","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Name_Space()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1"," ","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Name_Empty()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Name_WithDigit()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","Abg3","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }
    //Professor name tests

    @Test
    public void shouldPass_ProfessorName_NoSpace()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"abc","abc def","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }
    @Test
    public void shouldPass_ProfessorName_StringsWithSpace()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abcdef","1000","abc","abc bc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_ProfessorName_Space()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","abc"," "};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_ProfessorName_Empty()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","abc",""};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_ProfessorName_WithDigit()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","abc","abc4"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);
    }

    //e-mail tests
    @Test
    public void shouldPass_Email_CorrectFormat()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc@abc.com","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            gotThere = true;
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();

        }
        assertTrue(gotThere);

    }
    @Test
    public void shouldFail_Email_JustRandomString()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","abc","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Email_Space()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000"," ","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

    @Test
    public void shouldFail_Email_EmptyString()
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        String[] params={"1","abc","1000","","abc"};
        boolean gotThere = false;
        try{
            stsrv.add(params);
            fail();
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            gotThere = true;

        }
        assertTrue(gotThere);

    }

*/

}