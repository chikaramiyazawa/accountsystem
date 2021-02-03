package controllers.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Account;
import utils.DBUtil;

public class AccountValidator {
    public static List<String>validate(Account a ,Boolean numbers_check_flag ,Boolean password_check_flag){
        List<String>errors = new ArrayList<String>();

        String numbers_error = _validateNumber(a.getNumbers(),  numbers_check_flag);
        if(!numbers_error.equals("")){
            errors.add(numbers_error);
        }

        String password_error = _validatePassword(a.getPassword(),  password_check_flag);
        if(!password_error.equals("")){
        errors.add(password_error);
    }
    return errors;

}
private static String _validateNumber(String numbers, Boolean numbers_check_flag){
    if(numbers == null || numbers.equals("")){
        return "口座番号を入力してください。";
    }

    if(numbers_check_flag){
        EntityManager em = DBUtil.createEntityManager();
        long numbers_count = (long)em.createNamedQuery("checkNumber" , Long.class)
                                    .setParameter("numbers", numbers)
                                    .getSingleResult();
        em.close();
        if(numbers_count > 0){
            return "入力された口座番号の情報はすでに存在しています。";
        }
    }
    return "";
 }


private static String _validatePassword(String password, Boolean password_check_flag){
    if(password_check_flag && (password == null || password.equals(""))){
        return "パスワードを入力してください。";
    }
    return "";
}
}
