package model;

public class Task {

//	タスクを表現するモデルクラスです。タスクは一意のID、説明、完了状態の3つのフィールドを持ちます。このクラスを通じて、タスクの情報を保持し、管理します。
	
	//属性（フィールド）
	private int id; //タスクの一意のID。データベースでタスクを識別するために使用します。 
	private String description; //タスクの説明。タスクの内容を示します。
	private boolean status; //タスクの完了状態を表す。trueは完了、falseは未完了を意味します。

	
	// IDありのコンストラクタ
	public Task(int id, String description, boolean status) {
		//指定されたID、説明、完了状態を持つ新しいTaskオブジェクトを生成します。
		this.id = id;
		this.description = description;
		this.status = status;
	}
	
	// IDなしのコンストラクタを追加
	public Task(String description, boolean status) {
	    this.description = description;
	    this.status = status;
	}


	// タスクのIDを取得
	public int getId() {
		return this.id;
	}

	// タスクのIDを設定
	public void setId(int id) {
		this.id = id;
	}

	// タスクの説明を取得
	public String getDescription() {
		return this.description;
	}

	// タスクの説明を設定
	public void setDescription(String description) {
		this.description = description;
	}

	// タスクの完了状態を取得
	// タスクが完了している場合はtrue、未完了の場合はfalseを返す
	public boolean isStatus() {
		return status;
	}

	// タスクの完了状態を取得
	// 新しく設定するタスクの完了状態（true: 完了, false: 未完了）
	public void setStatus(boolean status) {
		this.status = status;
	}

	//// タスクの情報を文字列形式で取得
	// タスクのID、説明、完了状態を含む文字列を返す
	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", description='" + description + '\'' +
				", status=" + (status ? "Completed" : "Not Completed") +
				'}';
	}
}