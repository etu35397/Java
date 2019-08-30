package main.TestUnitaire;

import main.java.exception.MembershipValidator;
import main.java.exception.MembershipValidatorException;
import main.java.model.join.MembershipJoin;
import main.java.model.join.PizzaJoin;
import org.junit.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PurchaseOrderValidatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testValidMembership() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithoutMembership() throws MembershipValidatorException {
        MembershipValidator.validate(null);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithBoitePizzeriaA4Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setBoitePizzeria("999ergjs");
        MembershipValidator.validate(membershipJoin);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithBoiteLivrA4Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setBoite("999ergjs");
        MembershipValidator.validate(membershipJoin);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithRueLivrA4Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setNumRue("999ergjs");
        MembershipValidator.validate(membershipJoin);
    }

    @Test
    public void testWithNomReservA254Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setNomReservation(Utils.generateLongString(254));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test
    public void testWithNomReservA255Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setNomReservation(Utils.generateLongString(255));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithNomReservA256Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setNomReservation(Utils.generateLongString(256));
        MembershipValidator.validate(membershipJoin);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithNomReservEmpty() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setNomReservation("");
        MembershipValidator.validate(membershipJoin);
    }

    @Test
    public void testWithRueA254Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRue(Utils.generateLongString(254));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test
    public void testWithRueA255Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRue(Utils.generateLongString(255));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithRueA256Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRue(Utils.generateLongString(256));
        MembershipValidator.validate(membershipJoin);
    }

    @Test
    public void testWithRuePizzA254Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRuePizzeria(Utils.generateLongString(254));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test
    public void testWithRuePizzA255Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRuePizzeria(Utils.generateLongString(255));
        Assert.assertTrue(MembershipValidator.validate(membershipJoin));
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithRuePizzA256Char() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setRuePizzeria(Utils.generateLongString(256));
        MembershipValidator.validate(membershipJoin);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithWrongDate() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setDateLivraison(new GregorianCalendar(1,1,1,1,1));
        MembershipValidator.validate(membershipJoin);
    }

    @Test (expected = MembershipValidatorException.class)
    public void testWithEmptyPizzaList() throws MembershipValidatorException {
        MembershipJoin membershipJoin = Utils.createFakeMembershipJoin();
        membershipJoin.setPizzaList(new ArrayList<PizzaJoin>());
        MembershipValidator.validate(membershipJoin);
    }
}
