package org.docksidestage.app.web.profile;

import java.time.LocalDateTime;
import java.util.List;

import org.docksidestage.dbflute.exentity.Member;
import org.docksidestage.dbflute.exentity.MemberService;
import org.docksidestage.dbflute.exentity.Purchase;

/**
 * @author y.shimizu
 */
public class ProfileBean {

    final private String memberName;
    final private String memberStatusName;
    final private Integer servicePointCount;
    final private String serviceRankName;

    private List<PurchasedProductBean> purchaseList;

    public ProfileBean(Member member) {
        this.memberName = member.getMemberName();
        this.memberStatusName = member.getMemberStatus().get().getMemberStatusName();
        MemberService memberService = member.getMemberServiceAsOne().get();
        this.servicePointCount = memberService.getServicePointCount();
        this.serviceRankName = memberService.getServiceRank().get().getServiceRankName();
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberStatusName() {
        return memberStatusName;
    }

    public Integer getServicePointCount() {
        return servicePointCount;
    }

    public String getServiceRankName() {
        return serviceRankName;
    }

    public List<PurchasedProductBean> getPurchaseList() {
        return purchaseList;
    }
    public void setPurchaseList(List<PurchasedProductBean> purchaseList) {
        this.purchaseList = purchaseList;
    }
    public static class PurchasedProductBean {
        private String productName;
        private Integer regularPrice;
        private LocalDateTime purchaseDateTime;

        public PurchasedProductBean(Purchase purchase) {
            this.productName = purchase.getProduct().get().getProductName();
            this.regularPrice = purchase.getProduct().get().getRegularPrice();
            this.purchaseDateTime = purchase.getPurchaseDatetime();
        }

        public String getProductName() {
            return productName;
        }
        public void setProductName(String productName) {
            this.productName = productName;
        }
        public Integer getRegularPrice() {
            return regularPrice;
        }
        public void setRegularPrice(Integer regularPrice) {
            this.regularPrice = regularPrice;
        }
        public LocalDateTime getPurchaseDateTime() {
            return purchaseDateTime;
        }
        public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
            this.purchaseDateTime = purchaseDateTime;
        }
    }
}
