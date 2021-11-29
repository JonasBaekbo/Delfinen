package accounting;

import Domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionFeeTest {

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "19", "Motionist", "Aktivt");

        int expected = 1600;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "20", "Motionist", "Passivt");

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "65", "Motionist", "Aktivt");

        int expected = 1200;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "65", "Motionist", "Passivt");

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "60", "Motionist", "Aktivt");

        int expected = 1200;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemUnder18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "15", "Motionist", "Aktivt");

        int expected = 1000;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemUnder18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "15", "Motionist", "Passivt");

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "18", "Motionist", "Aktivt");

        int expected = 1600;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    //TODO: test med fejl indput?

}