package shared.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckerTest
{

  private static Checker checker;

  @BeforeAll public static void setup()
  {
    checker = new Checker();
  }

  @Test public void is_Letter_TestingCharactersThatAreLetters()
  {
    for (int i = 65; i < 123; i++)
    {
      char character = (char) i;
      assertTrue(checker.is_Letter(character));
      if (i == 90)
        i = 97;
    }
  }

  @Test public void is_Letter_TestingCharactersThatAreNotLetters()
  {
    for (int i = 32; i <= 255; i++)
    {
      char character = (char) i;
      assertFalse(checker.is_Letter(character));
      if (i == 64)
        i = 91;
      else if (i == 96)
        i = 123;
    }
  }

  @Test public void is_Digit_TestingCharactersThatAreDigits()
  {
    for (int i = 48; i < 58; i++)
    {
      char character = (char) i;
      assertTrue(checker.is_Digit(character));
    }
  }

  @Test public void is_Digit_TestingCharactersThatAreNotDigits()
  {
    for (int i = 32; i <= 255; i++)
    {
      char character = (char) i;
      assertFalse(checker.is_Digit(character));
      if (i == 47)
        i = 58;
    }
  }

  @Test public void is_Whitespace_TestingWhitespace()
  {
    char character = (char) 32;
    assertTrue(checker.is_WhiteSpace(character));
  }

  @Test public void is_Whitespace_TestingCharactersThatAreNotWhitespace()
  {
    for (int i = 33; i < 255; i++)
    {
      char character = (char) i;
      assertFalse(checker.is_WhiteSpace(character));
    }
  }

  @Test public void is_Underscore_TestingUnderscore()
  {
    char character = (char) 95;
    assertTrue(checker.is_underscore(character));
  }

  @Test public void is_Underscore_TestingCharactersThatAreNotUnderscore()
  {
    for (int i = 32; i <= 255; i++)
    {
      char character = (char) i;
      assertFalse(checker.is_underscore(character));
      if (i == 94)
        i = 96;
    }
  }

  @Test public void isValidPassword_Contains_Zero_Characters()
  {
    assertTrue(checker.isValidPassword(""));
  }

  @Test public void isValidPassword_Contains_One_InvalidCharacter()
  {
    assertFalse(checker.isValidPassword("!"));
  }

  @Test public void isValidPassword_Contains_Many_InvalidCharacters()
  {
    assertFalse(checker.isValidPassword("!@{:@}{"));
  }

  @Test public void isValidPassword_Contains_One_ValidCharacter()
  {
    assertTrue(checker.isValidPassword("a"));
  }

  @Test public void isValidPassword_Contains_Many_ValidCharacters()
  {
    assertTrue(checker.isValidPassword("somePassword"));
  }

  @Test public void isValidLoginName_Contains_Zero_Characters()
  {
    assertTrue(checker.isValidName(""));
  }

  @Test public void isValidLoginName_Contains_One_Invalid_Character()
  {
    assertFalse(checker.isValidName(" "));
  }

  @Test public void isValidLoginName_Contains_Many_Invalid_Characters()
  {
    assertFalse(checker.isValidName("@{:@}~@   @?><"));
  }

  @Test public void isValidLoginName_Contains_One_Valid_Character()
  {
    assertTrue(checker.isValidName("a"));
  }

  @Test public void isValidLoginName_Contains_Many_Valid_Characters()
  {
    assertTrue(checker.isValidName("some_loginname"));
  }

  @Test public void isValidFullName_Contains_Zero_Characters()
  {
    assertTrue(checker.isValidFullName(""));
  }

  @Test public void isValidFullName_Contains_One_Valid_Character()
  {
    assertTrue(checker.isValidFullName(" "));
  }

  @Test public void isValidFullName_Contains_Many_Valid_Characters()
  {
    assertTrue(checker.isValidFullName("some name"));
  }

  @Test public void isValidFullName_Contains_One_Invalid_Character()
  {
    assertFalse(checker.isValidFullName("{"));
  }

  @Test public void isValidFullName_Contains_Many_Invalid_Characters()
  {
    assertFalse(checker.isValidFullName("{>@:{{{{{{}%^&*"));
  }

  @Test public void isValidEmail_Zero()
  {
    assertFalse(checker.isValidEmail(""));
  }

  @Test public void isValidEmail_One()
  {
    assertFalse(checker.isValidEmail("a"));
  }

  @Test public void isValidEmail_Many_TestingSomeEmailFormats()
  {
    assertFalse(checker.isValidEmail("aaaaaaa"));
    assertFalse(checker.isValidEmail("mail@marek..com"));
    assertFalse(checker.isValidEmail("mailmarek..com"));
    assertFalse(checker.isValidEmail("mail.marek..com"));
    assertFalse(checker.isValidEmail("mail@@marek..com"));
    assertFalse(checker.isValidEmail("@mail.com"));
    assertFalse(checker.isValidEmail("marek@.com"));
    assertFalse(checker.isValidEmail("@.com"));
    assertTrue(checker.isValidEmail("marek@mail.com"));
    assertTrue(checker.isValidEmail("marek@mail.com.co"));
    assertTrue(checker.isValidEmail("ma.rek@mail.com"));
  }

  @Test public void isValidTelNo_Zero()
  {
    assertFalse(checker.isValidTelNo(""));
  }

  @Test public void isValidTelNo_One()
  {
    assertFalse(checker.isValidTelNo("1"));
  }

  @Test public void isValidTelNo_Many()
  {
    assertFalse(checker.isValidTelNo("+45 00 00 00 0"));
    assertFalse(checker.isValidTelNo("+45 00 00 00 0a"));
    assertFalse(checker.isValidTelNo("45 00 00 00 00"));
    assertTrue(checker.isValidTelNo("+45 00 00 00 00"));
  }



}