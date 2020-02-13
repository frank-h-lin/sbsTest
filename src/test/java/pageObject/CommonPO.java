package pageObject;

import org.openqa.selenium.By;

import java.util.List;

public class CommonPO {
    public static By playIconLocator = By.cssSelector(".video-player__tile-play");
    public static By pauseIconLocator = By.cssSelector(".video-player__play-pause-icon");



    public static By bySelectOption(List<String> identifierList)  {
        String id = identifierList.get(0);
        String partialValue = identifierList.get(1);
        return By.xpath("//select[contains(@id,'"+id+"')]/option[text()[contains(.,'"+partialValue+"')]]");
    }

    public static By byRBtnAriaLabelAndValue(List<String> identifierList) {

        String partialAriaLabel = identifierList.get(0);
        String value = identifierList.get(1);
        return By.xpath("//mat-radio-button[@value='"+value+"'][contains(@aria-label,'"+partialAriaLabel+"')]");
    }


    public static By byRBtnGroupAndLabelName(List<String> identifierList) {
        String groupName = identifierList.get(0);
        String label = identifierList.get(1);
        return By.xpath("//mat-radio-group[contains(@name,'"+groupName+"')]/descendant::div[@class='mat-radio-label-content'][contains(text(),'"+label+"')]");
    }
    public static By byTagAndPartialId(List<String> identifierList){
        String id = identifierList.get(0);
        String tag = identifierList.get(2);
        return By.xpath("//"+tag+"[contains(@id,'"+id+"')]");
    }

    public static By byId(List<String> identifierList){
        String id = identifierList.get(0);
        return By.id(id);
    }

    public static By byName(List<String> identifierList){
        String name = identifierList.get(0);
        return By.name(name);
    }

    public static By byDatePicker(List<String> identifierList){
        String id = identifierList.get(0);
        return By.xpath("//input[contains(@id, '"+id+"')]");
    }

    public static By byLabelFor(List<String> identifierList){
        String labelFor = identifierList.get(0);
        return By.xpath("//label[contains(@for, '"+labelFor+"')]");
    }
    public static By today = By.className("mat-calendar-body-today");

    public static By byTagAndPartialText(List<String> identifierList){
        String text = identifierList.get(0);
        String tag = identifierList.get(2);
        return By.xpath("//"+tag+"[contains(text(), '"+text+"')]");
    }

}
