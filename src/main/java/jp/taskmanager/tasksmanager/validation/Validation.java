package jp.taskmanager.tasksmanager.validation;

import java.util.Map;

import org.springframework.stereotype.Service;

import jp.taskmanager.tasksmanager.model.TaskManagerModel;

@Service
public class Validation {
	
	public boolean validationModel(TaskManagerModel model, Map<String, String> errorItem){
		
		boolean checkValidation = false;
		
		if(model.getTaitoru().isEmpty() | null == model.getTaitoru()) {
			errorItem.put("taitoru","タイトルは必須です。");
			checkValidation = true;
		}else {
			if(model.getTaitoru().length() >=300) {
				errorItem.put("taitoru","タイトルは300文字以内で入力してください");
				checkValidation = true;
			}
			errorItem.put("taitoru","");
		}
		if(model.getNaiYou().isEmpty() | null == model.getNaiYou()) {
			errorItem.put("naiYou","内容は必須です。");
			checkValidation = true;
		}else {
			if(model.getNaiYou().length() >=3000) {
				errorItem.put("naiYou","内容は3000文字以内で入力してください");
				checkValidation = true;
			}
			errorItem.put("naiYou","");
		}
		
		if(model.getYuusenjuni().isEmpty() | null == model.getYuusenjuni()) {
			errorItem.put("yuusenjuni","優先順位は必須です。");
			checkValidation = true;
		}
		
		boolean checkFlg = false;
		if(model.getYoteiBi().isEmpty() | null == model.getYoteiBi()) {
			errorItem.put("yoteiBi","予定日は必須です。");
			checkValidation = true;
		}else {
			if(model.getYoteiBi().length() != 10) {
				errorItem.put("yoteiBi","予定日の形式に誤りがあります。ｙｙｙｙ－ｍｍ－ddの形式で指定ください。");
				checkValidation = true;
			}else {
				checkFlg = true;
				errorItem.put("yoteiBi","");
			}
		}
		
		boolean checkFlg1 = false;
		if(model.getKanryoBi().isEmpty() | null == model.getKanryoBi()) {
			errorItem.put("kanryoBi","");
		}else {
			if(model.getKanryoBi().length() != 10) {
				errorItem.put("kanryoBi","予定日の形式に誤りがあります。ｙｙｙｙ－ｍｍ－ddの形式で指定ください。");
				checkValidation = true;
			}else {
				checkFlg1 = true;
				errorItem.put("kanryoBi","");
			}
		}
		if(checkFlg == true && checkFlg1 == true) {
			if(model.getYoteiBi().compareTo(model.getKanryoBi()) > 0) {
				errorItem.put("kanryoBi","完了日は予定日以後の日付を入力して下さい。");
				return checkValidation = true;
			}
			errorItem.put("kanryoBi","");
		}
		return checkValidation;
		
	}

}
