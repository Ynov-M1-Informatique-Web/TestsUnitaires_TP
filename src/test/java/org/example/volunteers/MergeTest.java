package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTest {

    @Test
    public void test_mergeDuplicateByName() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Doe","John","Jojo","","043534534"));
        listUser.add(listUser.size(), new Volunteer("Doe","John","","john.doe@example.com",""));
        listUser.add(listUser.size(), new Volunteer("DOe","DO","","do.doe@example.com",""));
        listUser.add(listUser.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com",""));
        listUser.add(listUser.size(), new Volunteer("","","","Maxhild@en",""));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));

        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);

        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Doe","John","Jojo","john.doe@example.com","043534534"));
        listExpected.add(listExpected.size(), new Volunteer("DOe","DO","","do.doe@example.com",""));
        listExpected.add(listExpected.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com",""));
        listExpected.add(listExpected.size(), new Volunteer("","","","Maxhild@en",""));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));

        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeDuplicateByPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("MC","EV","","mark.evian@gmail.com","04697811"));
        listUser.add(listUser.size(), new Volunteer("Doe","John","Jojo","john.doe@example.com","04697811"));
        listUser.add(listUser.size(), new Volunteer("DOe","DO","","do.doe@example.com","34423"));
        listUser.add(listUser.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com","54554"));
        listUser.add(listUser.size(), new Volunteer("","","","Maxhild@en","234234"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com","01324"));

        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByPhoneNumber(listUser);

        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("MC","EV","Jojo","mark.evian@gmail.com,john.doe@example.com","04697811"));
        listExpected.add(listExpected.size(), new Volunteer("DOe","DO","","do.doe@example.com","34423"));
        listExpected.add(listExpected.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com","54554"));
        listExpected.add(listExpected.size(), new Volunteer("","","","Maxhild@en","234234"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com","01324"));

        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeMispelledNameEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3309847821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.net","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.laurens@example.net","3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeMispelledNamePhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.org","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821,3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeMispelledNamePhoneAndEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.net","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.laurens@example.net","3307837821,3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNameTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurens","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankFirstNameTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankFirstNameNewEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","","","muriel.lauren@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.lauren@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankFirstNameNewPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","","","muriel.laurens@example.org","3307837829"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821,3307837829"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameNewPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurens","","muriel.laurens@example.org","3307837829"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821,3307837829"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameNewEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurens","","muriel.lauren@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.lauren@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameNewEmailBlankFirstTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("","Laurens","","muriel.lauren@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.lauren@example.org,muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNamesNewEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","","","muriel.lauren@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.lauren@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNamesNewPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","","","muriel.laurens@example.org","3307837861"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821,3307837861"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNamesAndPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","","","muriel.laurens@example.org",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNamesAndEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","","","","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankNamesAndEmailIfBlankFirstTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("","","","","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameWeirdFirstNameAndEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurenz","","muriel.laurensz@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.laurensz@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameWeirdFirstNameAndEmailWithNicknameOnEmptyTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","Antonio","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurenz","","muriel.laurensz@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","Antonio","muriel.laurens@example.org,muriel.laurensz@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameWeirdFirstNameAndEmailWithNicknameTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurenz","Antonio","muriel.laurensz@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","Antonio","muriel.laurens@example.org,muriel.laurensz@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeBlankLastNameAndPhoneWeirdFirstNameAndEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("","Laurenz","","muriel.laurensz@example.org",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(listExpected.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listExpected.add(listExpected.size(), new Volunteer("","laurenz","","muriel.laurensz@example.org",""));

        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeBlankLastNameAndPhoneWeirdFirstNameAndEmailIfBlankIsFirstTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("","Laurenz","","muriel.laurensz@example.org",""));
        listUser.add(listUser.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("","laurenz","","muriel.laurensz@example.org",""));
        listExpected.add(listExpected.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }


    @Test
    public void mergeBlankFirstNameMultipleDifferentNamesTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","Marco","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Muriel","","","muriel.laurens@example.net","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listExpected.add(listExpected.size(), new Volunteer("Muriel","Marco","","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeFullFirstNameBlankEmailAndPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","Jojo","",""));
        listUser.add(listUser.size(), new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","Jojo","muriel.laurens@example.org","3307837821"));
        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeFullFirstNameBlankEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","Jojo","",""));
        listUser.add(listUser.size(), new Volunteer("Muriel","Laurens","","","3307837821"));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","Jojo","","3307837821"));
        assertEquals(listExpected, finalListUser);
    }
}
