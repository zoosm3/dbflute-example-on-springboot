package org.lastaflute.remoteapi;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.dbflute.utflute.core.PlainTestCase;
import org.lastaflute.remoteapi.LastaRemoteApi.RemoteApiSendReceiveValidationErrorException;
import org.lastaflute.remoteapi.LastaRemoteApiTest.Sea.DocksideStage;
import org.lastaflute.remoteapi.LastaRemoteApiTest.Sea.HangarStage;
import org.lastaflute.web.validation.Required;

/**
 * @author jflute
 */
public class LastaRemoteApiTest extends PlainTestCase {

    public void test_doValidate_basic() {
        // ## Arrange ##
        LastaRemoteApi remoteApi = new LastaRemoteApi(op -> {}, this);
        Sea sea = prepareSea();

        // ## Act ##
        // ## Assert ##
        remoteApi.doValidate(sea); // expects no exception
    }

    public void test_doValidate_failure_listElementFirst() {
        // ## Arrange ##
        LastaRemoteApi remoteApi = new LastaRemoteApi(op -> {}, this);
        Sea sea = prepareSea();
        sea.docksideList.get(0).showName = null;

        // ## Act ##
        // ## Assert ##
        assertException(RemoteApiSendReceiveValidationErrorException.class, () -> {
            remoteApi.doValidate(sea);
        }).handle(cause -> {
            assertContains(cause.getMessage(), "docksideList[0].showName");
        });
    }

    public void test_doValidate_failure_listElementLast() {
        // ## Arrange ##
        LastaRemoteApi remoteApi = new LastaRemoteApi(op -> {}, this);
        Sea sea = prepareSea();
        sea.docksideList.get(2).showName = null;

        // ## Act ##
        // ## Assert ##
        assertException(RemoteApiSendReceiveValidationErrorException.class, () -> {
            remoteApi.doValidate(sea);
        }).handle(cause -> {
            assertContains(cause.getMessage(), "docksideList[2].showName");
        });
    }

    private Sea prepareSea() {
        Sea sea = new Sea();
        sea.hangar = new HangarStage();
        sea.hangar.showName = "mystic";

        sea.docksideList = new ArrayList<>();
        {
            DocksideStage docksideStage = new DocksideStage();
            docksideStage.showName = "over";
            sea.docksideList.add(docksideStage);
        }
        {
            DocksideStage docksideStage = new DocksideStage();
            docksideStage.showName = "waiting";
            sea.docksideList.add(docksideStage);
        }
        {
            DocksideStage docksideStage = new DocksideStage();
            docksideStage.showName = "newyork";
            sea.docksideList.add(docksideStage);
        }
        return sea;
    }

    public static class Sea {

        @Valid
        @Required
        public HangarStage hangar;

        public static class HangarStage {

            @Required
            public String showName;
        }

        @Valid
        public List<DocksideStage> docksideList;

        public static class DocksideStage {

            @Required
            public String showName;
        }
    }
}
