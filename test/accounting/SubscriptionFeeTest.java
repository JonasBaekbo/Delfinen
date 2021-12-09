//@Adam Lasson
package accounting;

import domain.CompetitionSwimmer;
import domain.DisciplineEnum;
import domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionFeeTest {
    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver18() {
        Member member = new Member("Adam Lasson", "19", true);
        int expected = 1600;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver18() {
        Member member = new Member("Adam Lasson", "20", false);
        int expected = 500;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemOver60() {
        Member member = new Member("Adam Lasson", "65", true);
        int expected = 1200;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemOver60() {
        Member member = new Member("Adam Lasson", "65", false);
        int expected = 500;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå60() {
        Member member = new Member("Adam Lasson", "60", true);
        int expected = 1200;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemUnder18() {
        Member member = new Member("Adam Lasson", "15", true);
        int expected = 1000;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtMedlemUnder18() {
        Member member = new Member("Adam Lasson", "15", false);
        int expected = 500;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemPå18() {
        Member member = new Member("Adam Lasson", "18", true);
        int expected = 1600;
        int result = (int) member.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtKonkurrenceOver18() {
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson", "19", true, DisciplineEnum.BRYSTSVØMNING);
        int expected = 1600;
        int result = (int) competitionSwimmer.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_PassivtKonkurrenceOver18() {
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson", "20", false, DisciplineEnum.BRYSTSVØMNING);
        int expected = 500;
        int result = (int) competitionSwimmer.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtKonkurrenceOver60() {
        CompetitionSwimmer competitionSwimmer = new CompetitionSwimmer("Adam Lasson", "65", true, DisciplineEnum.CRAWL);
        int expected = 1200;
        int result = (int) competitionSwimmer.getSubscriptionFee();
        assertEquals(expected, result);
    }

    @Test
    public void testafCalculateSubscriptionFee_AktivtMedlemMedFejlIndput() {
            Member member = new Member("Adam Lasson","hej", true);
            int expected = 0;
            int result = (int) member.getSubscriptionFee();
            assertEquals(expected, result);
    }
}