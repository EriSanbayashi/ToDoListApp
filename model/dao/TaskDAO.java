package model.dao;

import java.sql.Connection; // データベース接続用のクラス
import java.sql.DriverManager; // データベース接続の確立に使用
import java.sql.PreparedStatement; // パラメータ化されたSQL文の実行に使用
import java.sql.ResultSet; // SQLクエリの結果を格納
import java.sql.SQLException; // SQL操作中のエラー処理に使用
import java.sql.Statement; // 単純なSQL文の実行に使用
import java.util.ArrayList; // 可変長のリスト作成に使用
import java.util.List; // リスト型のインターフェース

import model.Task; // タスクモデルクラスのインポート

public class TaskDAO {
	//タスク管理アプリケーションのデータベース操作を担当します。
	//具体的には、タスクの作成（追加）、読み取り（取得）、更新、削除のCRUD操作を提供します。
	//データベースにはPostgreSQLを使用し、JDBCを利用してデータベースと通信を行います。

	private static final String URL = "jdbc:postgresql://localhost:5432/todo_app"; // データベースURL
	private static final String USER = "postgres"; // データベースユーザー名
	private static final String PASSWORD = "fullhouse8"; // パスワード

	// getConnectionメソッド
	// PostgreSQLデータベースへの接続を取得します。
	// 使用するインポート:
	// - Connection: データベース接続
	// - DriverManager: データベース接続の管理
	// - SQLException: エラー処理
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace(); // 接続エラー時の例外処理
		}
		return connection; // 接続が成功すればConnectionオブジェクトが返される
	}

	// addTaskメソッド
	// 新しいタスクをデータベースに追加します。
	// 使用するインポート:
	// - Connection: データベース接続
	// - PreparedStatement: パラメータ化されたSQL文の実行
	// - SQLException: エラー処理
	public void addTask(Task task) {
		// タスクをデータベースに追加するSQL文
		String sql = "INSERT INTO tasks (description, status) VALUES (?, ?)";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			// SQL文内のプレースホルダに値を設定
			statement.setString(1, task.getDescription()); // 1つ目の ? にdescriptionをセット
			statement.setBoolean(2, task.isStatus()); // 2つ目の ? にstatusをセット
			// SQL文を実行してデータを挿入
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // SQL操作中にエラーが発生した場合の処理
		}
	}

	// getAllTasksメソッド
	// データベースからすべてのタスクを取得し、リストとして返します。
	// 使用するインポート:
	// - Connection: データベース接続
	// - Statement: 単純なSQL文の実行
	// - ResultSet: SQLクエリの結果を格納
	// - SQLException: エラー処理
	// - ArrayList: 可変長リストの作成
	// - List: リスト型インターフェース
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<>(); // タスクを格納するリストを初期化
		String sql = "SELECT * FROM tasks ORDER BY id"; // SQL文を定義
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			// 取得したデータをリストに追加
			while (resultSet.next()) {
				int id = resultSet.getInt("id"); // IDを取得
				String description = resultSet.getString("description"); // 説明を取得
				boolean status = resultSet.getBoolean("status"); // 完了状態を取得
				Task task = new Task(id, description, status);
				tasks.add(task); // リストにタスクを追加
			}
		} catch (SQLException e) {
			e.printStackTrace(); // SQLエラーが発生した場合の処理
		}
		return tasks; // すべてのタスクを格納したリストを返す
	}

	//updateTaskStatusメソッド
	//指定されたIDのタスクの完了状態を更新します。
	public void updateTaskStatus(int id, boolean status) {
		String sql = "UPDATE tasks SET status = ? WHERE id = ?";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setBoolean(1, status); // 更新する状態をセット
			statement.setInt(2, id); // 更新対象のタスクIDをセット
			statement.executeUpdate(); // SQL文を実行
		} catch (SQLException e) {
			e.printStackTrace(); // エラーが発生した場合の処理
		}
	}

	//deleteTaskメソッド
	//指定されたIDのタスクをデータベースから削除します
	public void deleteTask(int id) {
		String sql = "DELETE FROM tasks WHERE id = ?";
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id); // プレースホルダに削除対象のIDをセット
			statement.executeUpdate(); // SQL文を実行してデータを削除
		} catch (SQLException e) {
			e.printStackTrace(); // エラーが発生した場合の処理
		}
	}

}
