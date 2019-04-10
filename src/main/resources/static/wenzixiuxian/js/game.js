/**
 * game.js  闭包
 */

(function(win) {

	var game = {
		isPrinting: false,
		containerId: 'game',
		init: function(id) {
			this.containerId = id
			console.log('game init ... containner id : ' + this.containerId)
		},
		print: function(containerId, content, style, color, speed) {
			this.isPrinting = true
			typewriter.init(document.getElementById(this.containerId), {
				text: content,
				effect: style,
				speed: speed
			})
			console.log('time:' + content.length * speed)
			var that = this
			setTimeout(function() {
				that.isPrinting = false // 打印结束后恢复课打印状态
			}, content.length * speed)

		},
		clear: function() {
			if(this.isPrinting == true) {
				mui.toast('上个操作还未结束')
				return false
			}
			document.getElementById(this.containerId).innerHTML = ''
		},
		player: {
			name: '@皮王',
			life: 100,
			ap: 88,
			protect: 30,
			IQ: 50,
			experience: 50,
			level: 1,
			coin: 0
		},
		enemy: {
			level: 1,
			name: '[普通敌人]',
			life: 100,
			ap: 50,
			protect: 30,
			IQ: 10
		},
		showPlayerInfo: function(player) {
			if(this.isPrinting == true) {
				mui.toast('上个操作还未结束')
				return false
			}
			player = player || this.player
			var str = '玩家#信息&'
			str += ('名字：' + player.name + '&')
			str += ('血量：' + player.life + '&')
			str += ('攻击力：' + player.ap + '&')
			str += ('防御力：' + player.protect + '&')
			str += ('智力：' + player.IQ + '&')
			str += ('经验值：' + player.experience + '/100&')
			str += ('等级：' + player.level + '&')
			str += ('金币：' + player.coin + '&')
			//			console.log(str)
			this.print(this.containerId, str, 'rightBig', null, 10)
		},
		showEnemyInfo: function(enemy) {

		},
		fight: function(player, enemy) {
			//			this.createRandomEnemy()

			if(this.isPrinting == true) {
				mui.toast('莫急！上个操作还未结束...')
				return false
			}
			var playerHistory = this.player // 存放历史数据  战斗结束后恢复至历史数据
			this.player = player || this.player
			this.enemy = enemy || this.enemy
			var exIfWin = this.calExperienceIfWin(this.player, this.enemy)
			var str = '敌人#信息&'
			str += ('名字：' + this.enemy.name + '&')
			str += ('血量：' + this.enemy.life + '&')
			str += ('攻击力：' + this.enemy.ap + '&')
			str += ('防御力：' + this.enemy.protect + '&')
			str += ('智力：' + this.enemy.IQ + '&')
			str += ('等级：' + this.enemy.level + '&')
			str += '&&战斗信息：&&'
			var i = 1
			var flag = true
			while(flag) {
				if(i % 2 != 0) {
					var num = Math.random() * 100
					if(num > this.enemy.IQ) {
						str += (this.player.name + '使用技能' + this.randomSkillName() + '攻击了' +
							this.enemy.name + '一下,' + this.randomSkillDes(this.enemy.name) + '#造成了' +
							this.calHurtValue(this.player.ap, this.enemy.protect) + '点伤害&')
						this.enemy.life -= this.calHurtValue(this.player.ap, this.enemy.protect)
					} else {
						str += (this.player.name + '攻击了' + this.enemy.name + '一下#被' + this.enemy.name +
							this.randomMoveDes() + '走位躲开了&')
					}

					if(this.enemy.life <= 0) {
						this.player.experience += exIfWin
						str += ('&' + this.enemy.name + '被打死了#' + this.player.name + '赢了&')
						str += ('&获得了' + exIfWin + '经验#' + this.levelUp(this.player, playerHistory))

						flag = false
					}
				} else {
					var num = Math.random() * 100
					if(num > this.player.IQ) {
						str += (this.enemy.name + '使用技能' + this.randomSkillName() + '攻击了' + this.player.name + '一下,' + this.randomSkillDes(this.player.name) + '#造成了' +
							this.calHurtValue(this.enemy.ap, this.player.protect) + '点伤害&')
						this.player.life -= this.calHurtValue(this.enemy.ap, this.player.protect)
					} else {
						str += (this.enemy.name + '攻击了' + this.player.name + '一下#被' + this.player.name +
							this.randomMoveDes() + '走位躲开了&')
					}

					if(this.player.life <= 0) {
						str += (this.player.name + '被打死了#' + this.enemy.name + '赢了&')
						this.player = playerHistory
						flag = false
					}
				}
				str += '&'
				i++
			}

			//			console.log(str)
			this.print(this.containerId, str, 'normal', null, 100)
		},
		calHurtValue: function(ap, pr) { //  计算每次攻击造成的伤害
			return(ap - pr)
		},
		calExperienceIfWin: function(player, enemy) { // 计算赢了获得多少经验
			return Math.round((enemy.level / player.level) * 50)
		},
		levelUp: function(player, playerHis) { // 判断玩家的经验条 如果超过100 level +1  同时刷新经验条
			var tempEx = player.experience
			var levelNum = parseInt(player.experience / 100)
			if(levelNum >= 1) {

				this.player.level += levelNum
				this.player.experience = tempEx - (100 * levelNum)
				this.player.life = (playerHis.life + levelNum * 20)
				this.player.ap = (playerHis.ap + levelNum * 10)
				this.player.IQ = (playerHis.IQ + levelNum * 2)
				this.player.protect = (playerHis.protect + levelNum * 10)
				return '叼毛你升了' + levelNum + '级！&'

			} else {
				return ''
			}
			//			if(player.experience >= 100){
			//				player.level += 1
			//				player.experience -= 100
			//				return '叼毛你升级了！&'
			//			}else{
			//				return ''
			//			}
			//			this.player = player
		},
		createRandomEnemy: function() { // 随机创建不同等级的敌人
			var player = this.player
			var level = Math.floor(Math.random() * player.level + 1) + Math.floor(Math.random() * player.level / 2)

			this.enemy.level = level
			this.enemy.name = this.randomEnemyName()
			this.enemy.life = 100 + level * 20,
				this.enemy.ap = 50 + level * 5,
				this.enemy.protect = 30 + level * 5,
				this.enemy.IQ = 10 + level * 1

		},
		randomEnemyName: function() { // 随机获取敌人的名字
			var strs = ['[皮皮怪]', '[托儿索]', '[儿童劫]', '[寒冰射手]'
			, '[姨妈精]', '[长脸怪]', '[蛇皮怪]', '[棒槌怪]']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomSkillName: function() { // 随机获取技能名称
			var strs = ['<狂风绝息斩>', '<面目全非脚>', '<还我漂漂拳>'
			, '<德玛西亚之力>', '<断头台>', '<小学生之手>', '<疯狂乱锤>']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomSkillDes: function(name) { // 随机获取技能描述
			var strs = ['犹如一万只草泥马在' + name + '头上奔腾而过', '仿佛一万巴掌拍在' + name + '脸上'
			, '好似一万升的酸雨在' + name + '脸上胡乱的拍'
			, '像一道强烈的光束穿过' + name + '脆弱的身体'
			, '像一颗原子弹直接砸在' + name + '的脸上'
			,'周围皆都被他的吼声所震撼到了','空间大片开裂，狂风怒号，呼呼作响'
			,'战刀之上，火焰雄浑','浩浩荡荡的火焰，仿佛泄洪一般','火舌怒舔，发出阵阵焦灼臭味'
			,'仿佛是火山，堆积了数千年，一朝喷发，毁天灭地'
			,'一拳轰出，天空瞬间形成一道巨大的拳影，宛若黄金浇铸'
			,'仿佛金色龙影摧枯拉朽一般横冲直撞']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomEnemyDes: function() {
			var strs = ['一只野生的', '一匹家养的', '一坨味道极浓的','一个看似很吊的','一根棒棒锤是的']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomMoveDes: function() { // 随机获取走位描述
			var strs = ['灵活的', '蛇皮一般的', '妖娆的', '机灵的', '瓜皮一般的', '性感而且骚气的'
			,'令人窒息的','令人绝望的','让人琢磨不透的','让人一脸蒙蔽的']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomPlaceName: function() {
			var strs = ['峡谷之巅', '艾欧尼亚', '瓜皮镇', '妓女村', '亚欧大陆', '珠穆朗玛峰'
			,'沙漠','爱国村','洛杉矶','新加坡']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomGoAwayStr: function() {
			var strs = ['你是真滴捞！', '你是真的怂啊！', '垃圾废物！', '三十六计走位上计', '除了跑路你还会干嘛？？', '溜了溜了～']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomGoAwayFailStr: function() {
			var strs = ['逃跑失败，只能硬上了！', '卧槽，被逮到了，只能拼一波了']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomFaceStr:function(){
			var strs = ['嘴角露出一抹冰冷的笑意', '略带一丝嘲弄的表情', '猛地狂喝一声'
			, '猛地狂喝一声', '猛地狂喝一声', '猛地狂喝一声', '猛地狂喝一声']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		findWay: function() { // 寻路
			this.createRandomEnemy()

			document.getElementById(this.containerId).innerHTML = ''
			document.getElementById(this.containerId).innerHTML = '向前走了' +
				Math.floor(Math.random() * 100 + 1) + '里山路来到了' + this.randomPlaceName() + '&'

			var that = this
			var btnArray = ['干他', '认怂'];
			mui.confirm('遇到' + this.randomEnemyDes() + this.enemy.level + '级的' + this.enemy.name, 'fight', btnArray, function(e) {
				if(e.index == 1) {
					if(Math.random() >= 0.5) {
						mui.toast(that.randomGoAwayFailStr())
						that.fight()
					} else {
						mui.toast(that.randomGoAwayStr())
						that.findWay()
					}

				} else {
					that.clear()
					that.fight()
				}
			})

		}
	}

	win["Game"] = game

})(window);

Game.init("test")
//Game.showPlayerInfo()
//Game.fight()
//setTimeout(function(){
//	Game.clear()
//	Game.showPlayerInfo()
//},10000)
//Game.print('test',2,3)