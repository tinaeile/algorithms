package org.pg4200.ex10;

/**
 * Created by arcuri82 on 07-May-18.
 */
public interface PatternExamples {

    /**
     * Any sequence of letters C, G, A and T.
     * (cytosine [C], guanine [G], adenine [A] or thymine [T])
     * Should be at least one value.
     */
    String dnaRegex();

    /**
     *  8 digit number.
     *  Might be preceded by a country code, which
     *  is either a + or 00 followed by 2 digits
     */
    String telephoneNumberRegex();


    /**
     * An email is composed of, in order:
     * - a non-empty word (letters from a to z, upper and lower case, plus digits)
     * - followed by zero or more non-empty words, each one separated by a "."
     * - symbol @
     * - a non-empty word
     * - followed by zero or more non-empty words, each one separated by a "."
     * - a final domain code, which is at least 2 letters (upper or lower case), and no digits
     */
    String emailRegex();

    /**
     * Need match the following sentence:
     *
     * Is this an out of season april fools joke?
     *
     * However, the regex should be general enough to also match for further string variants following these properties:
     * a)	there can  be any number of spaces between the words
     * b)	each letter can be either in lower or upper case
     */
    String isItAJoke();
}
