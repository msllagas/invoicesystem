package sample.Model;

public class PaymentMethod {
    private String termsCode;
    private String termsDesc;

    public PaymentMethod() {
    }

    public PaymentMethod(String termsCode, String termsDesc) {
        this.termsCode = termsCode;
        this.termsDesc = termsDesc;
    }

    public String getTermsCode() {
        return termsCode;
    }

    public void setTermsCode(String termsCode) {
        this.termsCode = termsCode;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
    }
}
