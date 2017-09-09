/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regexvalidator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Morales
 */
public class Validator {
    
    private final boolean regex_bools[] = {true,true,true,true,true,true,true,true};
    private final String regex_names[] = {
        "una direccion IPv4",
        "una CURP",
        "una URL",
        "una fecha en formato mm/dd/aaaa",
        "un entero positivo",
        "un color en hexadecimal de HTML",
        "una tarjeta de credito",
        "un tag de HTML"
    };
    private final String card_names[] = {
        "VISA",
        "MasterCard",
        "AmericanExpress"
    };
    private final String card_regexes[] = new String[3];
    private final String regexes[] = new String[8];
    private final String IPv4_regex = "^\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b$";
    private final String CURP_regex = "^[A-Z][AEIOU][A-Z]{2}\\d{2}([0][1-9]|[1][0-2])([0][1-9]|(1|2)[0-9]|[3](0+1))(H|M)((AS)|(BC)|(BS)|(CC)|(CL)|(CM)|(CS)|(CH)|(DF)|(DG)|(GT)|(GR)|(HG)|(JC)|(MC)|(MN)|(MS)|(NT)|(NL)|(OC)|(PL)|(QT)|(QR)|(SP)|(SL)|(SR)|(TC)|(TS)|(TL)|(VZ)|(YN)|(ZS)|(NE))([B-DF-HJ-NP-TV-Z]){3}(\\d|[A-Z])\\d$"; // de acuerdo a https://es.wikipedia.org/wiki/Clave_%C3%9Anica_de_Registro_de_Poblaci%C3%B3n
    private final String URL_regex = "^(http(s)?(\\:\\/\\/))?((www|[a-zA-Z0-9]+)(\\.)?)?[a-zA-Z0-9-_]+((\\.)[a-zA-Z]{2,6})+(\\:\\d{1,4})?((\\/)([a-zA-Z0-9-_.?=]+)?)*$"; //de acuerdo a https://en.wikipedia.org/wiki/URL
    private final String Date_regex = "^(((0(1|3|5|7|8)|1(0|2)))\\/((0)[1-9]|(1|2)[0-9]|(3)[0-1])|(02)\\/((0)[1-9]|(1)[0-9]|(2)[0-8])|((0(4|6|9)|(11)))\\/((0)[1-9]|(1|2)[0-9]|(30)))\\/[0-9]{4}$";
    private final String Int_regex = "^\\+?[0-9]+$";
    private final String HTMLHex_regex = "^\\#[0-9a-fA-F]{6}$";
    private final String CC_regex = "^((4)([0-9]{12}|[0-9]{15}|[0-9]{18})|((5)[1-5][0-9]{14}|((222)[1-8][0-9]{2}|(2229)[0-8][0-9]|(22299)[0-9]|(22)[3-9][0-9]{3}|(2)[3-6][0-9]{4}|(27)[01][0-9]{3}|(2720)[0-8][0-9]|(27209)[0-9])[0-9]{10})|(3)(4|7)[0-9]{13})$"; //de acuerdo a https://www.freeformatter.com/credit-card-number-generator-validator.html
    private final String CC_Visa_regex = "^(4)([0-9]{12}|[0-9]{15}|[0-9]{18})$";
    private final String CC_MC_regex = "^(((5)[1-5][0-9]{14})|(((222)[1-8][0-9]{2}|(2229)[0-8][0-9]|(22299)[0-9]|(22)[3-9][0-9]{3}|(2)[3-6][0-9]{4}|(27)[01][0-9]{3}|(2720)[0-8][0-9]|(27209)[0-9])[0-9]{10}))$";
    private final String CC_AE_regex = "^(3)(4|7)[0-9]{13}$";
    private final String HTMLTag_regex = "^<(.|\\n)*?>$";
    
    public Validator() {
        regexes[0] = IPv4_regex;
        regexes[1] = CURP_regex;
        regexes[2] = URL_regex;
        regexes[3] = Date_regex;
        regexes[4] = Int_regex;
        regexes[5] = HTMLHex_regex;
        regexes[6] = CC_regex;
        regexes[7] = HTMLTag_regex;
        card_regexes[0] = CC_Visa_regex;
        card_regexes[1] = CC_MC_regex;
        card_regexes[2] = CC_AE_regex;
    }
    
    //0 -> boolean (valido o no), 1 -> string (con cual regex queda)
    public ArrayList validate(String q){
        ArrayList results = new ArrayList();
        boolean valid = false;
        String message = "";
        Pattern pattern;
        Matcher matcher;
        int x = 0;
        for(int j = 0; j<regex_bools.length; j++){
            for(int i = 0; i<regexes.length; i++){
                if(regex_bools[i]){
                    pattern = Pattern.compile(regexes[i]);
                    matcher = pattern.matcher(q);
                    if(matcher.matches()){
                        regex_bools[i] = false;
                        valid = true;
                        x = i;
                        message = message + "\n- " + regex_names[i] + (i==6?" ("+getCard(q)+")":"");                        
                        break;
                    }
                }
            }
        }
        results.add(valid);
        results.add(message);
        return results;
    }
    
    protected String getCard(String c){
        Pattern pattern;
        Matcher matcher;
        int x = 0;
        for(int i = 0; i<card_regexes.length; i++){
            pattern = Pattern.compile(card_regexes[i]);
            matcher = pattern.matcher(c);
            if(matcher.matches()){
                x = i;
                break;
            }
        }
        return card_names[x];
    }
}
