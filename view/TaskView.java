package view;

import java.util.List; // リスト型のインターフェース

import model.Task; // タスクモデルクラスのインポート

public class TaskView {
	//タスク管理アプリケーションのユーザーに対する表示処理を担当します。
	//コンソールにタスクの一覧を表示したり、操作結果のメッセージを表示したりする役割を持ちます。
	//MVCモデルにおける"View"の役割を担い、ユーザーインターフェースの一部として機能します。	

	//List<Task>`型のタスクリストを受け取り、各タスクの情報（ID、説明、完了状態）をコンソールに表示します。
	public void displayTasks(List<Task> tasks) {
		if (tasks == null || tasks.isEmpty()) {
			System.out.println("タスクがありません");
		} else {
			for (Task t : tasks) {
				System.out.println(t.toString());
			}
		}
	}

	//任意の文字列メッセージをコンソールに表示します。
	//タスクの追加、更新、削除時にユーザーに通知するために使用します。
	public void displayMessage(String message) {
		System.out.println(message);
	}

}
