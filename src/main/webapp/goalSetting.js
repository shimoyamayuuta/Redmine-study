/**
 * ブラウザがhtmlファイルを読み込んだ後に、pageLoad()を呼び出す
 */
document.addEventListener('DOMContentLoaded', pageLoad);

/**
 * 保存ボタンを押下することでイベントを発火させる<br>
 */
function pageLoad() {
	goalGet();
	const saveButton = document.getElementById('save-button');
	//イベントリスナーが登録されていない場合
	if (!saveButton.hasAttribute('eventListener')) {
		saveButton.setAttribute('eventListener', 'true');//イベントリスナー登録
		saveButton.addEventListener('click', goalSetSave);
	}
}

/**
 * 目標を取得する
 */
function goalGet() {
	fetch('http://localhost:8080/Redmine-Study/goal', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json'
			}
		})
			.then(response => response.json()) //レスポンスをJSON形式に変換
			.then(data => {
				const goalInput = document.getElementById('goal-input');
				goalInput.value = data.goalName;
			}) //レスポンス値を返却
			.catch(error => console.error('エラー:', error));
}

/**
 * 目標を作成する
 */
function goalSetSave() {
	const goalInput = document.getElementById('goal-input');
	//	console.log(goalInput.value);
	if (goalInput.value !== "") {
		console.log("セーブできました");
		// 入力した目標を保存する
		fetch('http://localhost:8080/Redmine-Study/save', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ goalSetting: goalInput.value }), // 送信するデータをJSON形式に変換
		})
			.then(response => response.json()) //レスポンスをJSON形式に変換
			.then(data => console.log(data)) //レスポンス値を返却
			.catch(error => console.error('エラー:', error));
	}
}
