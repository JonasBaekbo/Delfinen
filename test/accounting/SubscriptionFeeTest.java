//@Adam Lasson
package accounting;

import Domain.CompetitionSwimmer;
import Domain.DisciplineEnum;
import Domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionFeeTest {


    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "19", true);

        int expected = 1600;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "20", false);

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "65", true);

        int expected = 1200;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "65", false);

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "60", true);

        int expected = 1200;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemUnder18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "15", true);

        int expected = 1000;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemUnder18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "15", false);

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        Member member = new Member("Adam Lasson", "18", true);

        int expected = 1600;

        int result = (int) subscriptionFee.getSubscriptionFee(member);

        assertEquals(expected, result);
    }


    @Test
    public void testafCalculateSubscriptionFee_AktivtKonkurrenceOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson","19",true, DisciplineEnum.BRYSTSVØMNING);
        int expected = 1600;

        int result = (int) subscriptionFee.getSubscriptionFee(competitionSwimmer);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtKonkurrenceOver18() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson","20",false, DisciplineEnum.BRYSTSVØMNING);

        int expected = 500;

        int result = (int) subscriptionFee.getSubscriptionFee(competitionSwimmer);

        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtKonkurrenceOver60() {

        SubscriptionFee subscriptionFee = new SubscriptionFee();
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson","65",true, DisciplineEnum.CRAWL);

        int expected = 1200;

        int result = (int) subscriptionFee.getSubscriptionFee(competitionSwimmer);

        assertEquals(expected, result);
    }
}