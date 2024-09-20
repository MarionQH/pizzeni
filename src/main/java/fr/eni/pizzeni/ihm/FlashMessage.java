package fr.eni.pizzeni.ihm;

public class FlashMessage {
    public static final int TYPE_FLASH_SUCESS = 0;
    public static final int TYPE_FLASH_ERROR = 1;

    public int type;
    public String message;

    public FlashMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    //Fonction qui sert uniquement pour le front, permet de selectioner quelle classe CSS il faut utiliser.
    //Selonn le type du message.

    public String getTypeCssClass(){
        if(type == TYPE_FLASH_SUCESS){
            return "uk-alert-success";
        }
        if(type == TYPE_FLASH_ERROR){
            return "uk-alert-danger";
        }
        return "uk-alert-primary";
    }

}
