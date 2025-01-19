/**
 * ブラウザがindent.htmlを読み込んだ後に、pageLoad()を呼び出す
 */
document.addEventListener('DOMContentLoaded', pageLoad);

/**
 * 入力ボックス内でキー入力することでイベントを発火させる<br>
 * １行目の入力boxにイベントを設定することで、キー入力操作によってイベントが発火するようにする
 */
function pageLoad() {
	const firstInput = document.getElementById('first-input');
	//firstInputのイベントリスナーが登録されていない場合
	if (!firstInput.hasAttribute('eventListener')) {
		firstInput.setAttribute('eventListener', 'true');//イベントリスナー登録
		firstInput.addEventListener('keydown', (event) => enterKeyPress(event, firstInput));
	}
}

/**
 * 現在選択中の入力ボックスを明確にする<br>
 * 現在選択中の入力boxにイベントを設定することで、キーボード入力操作によってイベントが発火するようにする
 */
function curFocusElement() {
	const curInput = document.activeElement;
	//curInputのイベントリスナーが登録されていない場合
	if (!curInput.hasAttribute('eventListener')) {
		curInput.setAttribute('eventListener', 'true');//イベントリスナー登録
		curInput.addEventListener('keydown', (event) => enterKeyPress(event, curInput));
	}
}

let lineNo = 2;

/**
 * 箇条書きを階層構造Listに１行追加する<br>
 * 箇条書き内でのEnterキー操作によって箇条書きを階層構造Listに１行追加する
 */
function enterKeyPress(event, curInput) {
	const treeUl = document.getElementById("tree-ul");
	const newInput = document.createElement("input");
	const newLi = createLi(newInput);
	const curLi = curInput.parentElement;
	if (event.key == 'Enter') {
		let indentUpCount = 0;
		let nonFirstLevelfFlg = false;
		let currentLi = curInput.parentElement;
		while (currentLi) {
			// List一覧が選択されている場合
			if (currentLi.id === 'tree-ul') {
				// 3+2の倍数（第2階層：3インデント上るとList一覧 / 第3階層以降；＋2インデント上るとList一覧）
				let flgCalc = (indentUpCount - 3) % 2;
				// 選択中の箇条書きが第2階層以下の場合
				if (Object.is(flgCalc, 0)) {
					nonFirstLevelfFlg = true;
				}
				break;
			} else {
				currentLi = currentLi.parentElement;
			}
			indentUpCount++;
		}

		// 第2階層以下の1行追加
		if (nonFirstLevelfFlg) {
			//選択行直下に1行も箇条書きがない場合
			if (curLi.nextElementSibling === null) {
				//選択中の階層の最後尾に1行追加
				const addLi = curLi.parentElement;
				addLi.appendChild(newLi);
			}
			else {
				//選択行直下に1行追加
				curLi.after(newLi);
			}
		} // 第1階層最終行への1行追加
		else if (curLi.nextElementSibling === null) {
			newLi.id = lineNo;
			treeUl.appendChild(newLi);
			lineNo++;
		} // 第1階層最終行以外への1行追加
		else {
			let curLineNo = parseInt(curLi.id);
			newLi.id = curLineNo + 1;
			treeUl.insertBefore(newLi, treeUl.children[curLineNo]);
			// 追加する行以下の行数Noの整理
			for (let j = curLineNo + 1; j < treeUl.childElementCount; j++) {
				let charId = treeUl.children[j].id;
				treeUl.children[j].id = (parseInt(charId) + 1).toString();
			}
			lineNo++;
		}
		newInput.focus();
		console.log(treeUl);
	}
	else if (event.key == 'Tab') {
		// Tabキーのデフォルト動作を無効化
		event.preventDefault();
		// 階層を一段落とした上で箇条書きを1行追加
		const childUl = document.createElement("ul");
		childUl.appendChild(newLi);
		curLi.appendChild(childUl);

		newInput.focus();
	}
	curFocusElement();
}

/**
 * 箇条書きを１行新規作成する<br>
 * 階層構造Listに箇条書きを１件ずつ追加したい時に利用するメソッド
 */
function createLi(newInput) {
	const newLi = document.createElement("li");
	newLi.appendChild(newInput);
	return newLi;
}


