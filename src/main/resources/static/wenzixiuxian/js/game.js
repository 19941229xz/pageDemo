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
			name: '盾天老祖',
			life: 100,
			ap: 68,
			protect: 52,
			IQ: 15,
			experience: 20,
			level: 1,
			coin: 0
		},
		enemy: {
			level: 1,
			name: '[普通敌人]',
			life: 100,
			ap: 49,
			protect: 34,
			IQ: 12,
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

					if(this.enemy.life <= 0){
						this.player.experience += exIfWin
						str += ('&' + this.enemy.name + '被打死了#' + this.player.name + '赢了&')
						str += ('&获得了' + exIfWin + '经验#' + this.levelUp(this.player, playerHistory))
						str += ('战斗胜利，小息一会儿，血量恢复' + (this.player.level * 12) + '&')

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
						alert('你娃挂了')
						 window.location.reload()
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
			if((ap - (Math.floor(pr * 0.4))) <= 0){
				return 5
			}
			return ap - (Math.floor(pr * 0.4))
		},
		calExperienceIfWin: function(player, enemy) { // 计算赢了获得多少经验
			return Math.round((enemy.level / player.level) * 65)
		},
		levelUp: function(player, playerHis) { // 判断玩家的经验条 如果超过100 level +1  同时刷新经验条
			var tempEx = player.experience
			var levelNum = parseInt(player.experience / 100)
			if(levelNum >= 1) {

				this.player.level += levelNum
				this.player.experience = tempEx - (100 * levelNum)
				this.player.life = (playerHis.life + levelNum * 18)
				this.player.ap = Math.floor(playerHis.ap + levelNum * 3)
				this.player.IQ = Math.floor(playerHis.IQ + levelNum * 0.2)
				this.player.protect = Math.floor(playerHis.protect + levelNum * 5)
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
			this.enemy.life = 100 + level * 12,
				this.enemy.ap = 38 + level * 2,
				this.enemy.protect = Math.floor(34 + level * 1.7),
				this.enemy.IQ = Math.floor(10 + level * 0.3)

		},
		randomEnemyName: function() { // 随机获取敌人的名字
			var strs = ['[海龙]', '[昊天]', '[虚神子]', '[火灵]'
			, '[贝塔]', '[凌天]', '[伽罗]']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomSkillName: function() { // 随机获取技能名称
			var strs = ['<古神一指>', '<十万尊魂帆>', '<化魔>'
			, '<虚火>', '<翻天>', '<井底捞月>', '<焚天伞>']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomSkillDes: function(name) { // 随机获取技能描述
			var strs = ['召唤出九条黑龙飓风在' + name + '身边旋转，突然冲向' + name + '身体'
			, '召唤出千万魂魄朝向' + name + '嘶吼'
			, '周身出现了无穷的虚幻火焰，将' + name + '包围在其中'
			,'周遭景物仿佛置入水底，空中出现一只大手将' + name +'从中抓起'
			,'伞开出现无尽的火焰将所在之地全部焚烧殆尽'
			,'仿佛天地互换，万物出现了倾斜，让人心神受损'
			,'一指指出，空中出现了巨大的虚影，宛如古神一指'
			,'顷刻间，仿佛变为魔一般，身体周围弥漫着黑气，向着' + name + '冲去']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomEnemyDes: function() {
			var strs = ['一个大耳修士', '一个来自远古家族的', '一个从秘境逃出的','一个看似很吊的','一个获得仙力的']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomMoveDes: function() { // 随机获取走位描述
			var strs = ['灵活的', '蛇皮一般的', '妖娆的', '机灵的', '瓜皮一般的', '性感而且骚气的'
			,'令人窒息的','令人绝望的','让人琢磨不透的','让人一脸蒙蔽的']
			return strs[Math.floor(Math.random() * strs.length)]
		},
		randomPlaceName: function() {
			var strs = ['朱雀圣地', '古神之地', '炼魂宗', '蓝丝族', '远古仙域', '古仙界'
			,'雨界','皇城',]
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

		},
		enhance:function(){
			mui.toast('暂未达到修炼的等级')
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