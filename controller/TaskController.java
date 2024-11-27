package controller;

import java.util.List;

import model.Task;
import model.dao.TaskDAO;
import view.TaskView;

public class TaskController {
	//タスク管理アプリケーションにおけるビジネスロジックを担当し、
	//モデル（`TaskDAO`）とビュー（`TaskView`）を仲介します。
	//ユーザーからの入力に基づき、データベース操作と表示操作を調整します。

	// 属性（フィールド）
	private TaskDAO taskDAO; //データベースとのやり取りを行うためのオブジェクト。                   |
	private TaskView taskView; // ユーザーにメッセージを表示するためのオブジェクト。                   |

	//コンストラクタ
	public TaskController(TaskDAO taskDAO, TaskView taskView) {
		this.taskDAO = taskDAO;
		this.taskView = taskView;
	}

	//新しいタスクを追加し、成功メッセージを表示します。
	public void addTask(String description) {

		// 新しいタスクを作成。状態は「未完了」として false をセット
		Task task = new Task(description, false);
		taskDAO.addTask(task); // データベースにタスクを保存

		// 成功メッセージを表示
		taskView.displayMessage("タスクが追加されました: " + description);
	}

	//すべてのタスクを取得し、ユーザーに一覧表示します。
	public void showAllTasks() {

		//データベースからタスク一覧を取得
		List<Task> tasks = taskDAO.getAllTasks();

		//取得したタスク一覧を表示
		taskView.displayTasks(tasks);
	}

	//指定したIDのタスクの状態を更新し、成功メッセージを表示します。
	public void updateTaskStatus(int id, boolean status) {

		//指定したタスクの状態を更新
		taskDAO.updateTaskStatus(id, status);

		//タスクの状態更新のメッセージを表示
		taskView.displayMessage("タスクが更新されました");
	}

	//指定したIDのタスクを削除し、成功メッセージを表示します。
	public void deleteTask(int id) {

		//指定したタスクをデータベースから削除
		taskDAO.deleteTask(id);

		//タスク削除のメッセージを表示
		taskView.displayMessage("タスクが削除されました");

	}
}