package fr.eni.pizzeni.ihm;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class IHMHelpers {
    /**
     * fonction pour afficher un message lors de la connexion ou de la déconection.
     * @param redirectAttributes retaper juste redirectAttributes
     * @param type SUCCES ou ERROR constante de la classe EniFlashMessage
     * @param message à taper à la main
     */
    public static void sendCommonFlashMessage(RedirectAttributes redirectAttributes, int type, String message) {
        redirectAttributes.addFlashAttribute("flashMessage", new FlashMessage(type,message));
    }

}
