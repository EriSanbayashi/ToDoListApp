package main;

import java.util.Scanner;

import controller.TaskController;
import model.dao.TaskDAO;
import view.TaskView;

public class TodoApp {

	public static void main(String[] args) {
		//タスク管理アプリケーションのメインクラスであり、アプリケーションのエントリーポイントです。
		//コンソールからユーザーの入力を受け付け、タスクの追加、一覧表示、状態更新、削除を実行します。
		//ユーザーの選択に基づいて、`TaskController`を通じて各操作を実行します。

		// オブジェクトの初期化
		TaskDAO taskDAO = new TaskDAO(); // データベース操作を担当するオブジェクト
		TaskView taskView = new TaskView(); // ユーザーへの表示を担当するオブジェクト
		TaskController taskController = new TaskController(taskDAO, taskView); // ビジネスロジックを処理するオブジェクト
		Scanner scanner = new Scanner(System.in); // コンソール入力を受け取るためのオブジェクト
		boolean exit = false; // アプリケーションの終了を管理するフラグ

		while (!exit) {
			//メニュー表示
			System.out.println("1: タスクの追加\n2: タスク一覧の表示\n3: タスクの状態更新\n4: タスクの削除\n5: 終了");

			// ユーザーの選択を受け取る
			System.out.print("選択肢を入力してください: ");

			int choice = scanner.nextInt(); // ユーザーの入力を取得
			scanner.nextLine(); // 改行をクリアする

			if (choice < 1 || choice > 5) {
				System.out.println("選択肢から選んでください"); // メッセージを表示
				continue; // ループをスキップして次の入力を待つ

			} else {

				switch (choice) {
				
				case 1:
				    // タスクの追加処理
				    System.out.print("タスクの内容を入力してください: ");
				    String taskDescription = scanner.nextLine().trim(); // 余分な改行のクリアはすでに上で行われているため、ここはシンプルに取得する
				    if (taskDescription.isEmpty()) {
				        System.out.println("タスクの内容は空ではいけません。再度入力してください");
				    } else {
				        taskController.addTask(taskDescription);
				    }
				    break;

				case 2:
					// タスク一覧の表示
					taskController.showAllTasks();
					break;

				case 3:
					// タスクの状態更新
					System.out.print("更新するタスクのIDを入力してください: ");
					int taskId = Integer.parseInt(scanner.nextLine()); // ユーザーの入力を数値に変換
					System.out.print("新しい状態を入力してください (trueまたはfalse): ");
					boolean taskStatus = Boolean.parseBoolean(scanner.nextLine()); // ユーザーの入力をbooleanに変換
					taskController.updateTaskStatus(taskId, taskStatus);
					break;

				case 4:
					// タスクの削除
					System.out.print("削除したいタスクのIDを入力してください: ");
					int deleteId = Integer.parseInt(scanner.nextLine()); // ユーザーの入力を数値に変換
					taskController.deleteTask(deleteId);
					break;

				case 5:
					exit = true; // ループを終了する
					System.out.println("アプリケーションを終了します。");
					break;

				default:
					System.out.println("無効な選択です。再度選んでください。");
				}
			}
		}
	}
}
