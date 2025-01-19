/**
 * ブラウザがhtmlファイルを読み込んだ後に、pageLoad()を呼び出す
 */
document.addEventListener('DOMContentLoaded', pageLoad);

/**
 * 保存ボタンを押下することでイベントを発火させる<br>
 */
function pageLoad() {
	const saveButton = document.getElementById('save-button');
	//イベントリスナーが登録されていない場合
	if (!saveButton.hasAttribute('eventListener')) {
		saveButton.setAttribute('eventListener', 'true');//イベントリスナー登録
		saveButton.addEventListener('click', goalSetSave);
	}
}

function goalSetSave() {
	const goalInput = document.getElementById('goal-input');
	//	console.log(goalInput.value);
	if (goalInput.value !== "") {
		console.log("セーブできました");
		//fetchでバックエンドにデータ送信
		fetch('http://localhost:8080/goal', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({ goal: '目標を入力' }), // 送信するデータ
			mode: 'cors' // CORSを明示的に設定
		})
			.then(response => response.json())
			.then(data => console.log(data))
			.catch(error => console.error('エラー:', error));
	}
}

