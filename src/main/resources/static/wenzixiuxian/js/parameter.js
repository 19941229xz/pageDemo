let typewriter = (function(){
	
	// 构造函数
	let Typewriter = function(el,options) {
		this.$el = $(el);
		this.$options = $.extend({},Typewriter.DEFAULTS,options);
	}
	// 默认参数
	Typewriter.DEFAULTS = {
        text:  '这里是默认出现的文字内容，用于测试。',
		effect: 'upBig',
		speed: 100,
		delay: 0,
		excute: function() {},
		complete: function() {},
	}
	
	// 支持的效果
	Typewriter.SUPPORT = ['fadeInLeftBig','fadeInRightBig','fadeInUpBig','fadeInDownBig','fadeInLeft','fadeInRight','fadeInDown','fadeInUpBig','fadeInDown','fadeInUp'];
	
	// 定时器
	let timer = -1;
	
	// 添加特效 
	Typewriter.prototype.addEffect = function() {
//		console.log(this.$options.text)
		let words = this.$options.text.split(''),
			count = 0;
			console.log('length:'+words.length)
		timer = setInterval(() => {
			let effect = this.$options.effect === 'normal'? '' : 'animated ';
			if (this.$options.effect === 'random') {
				effect += Typewriter.SUPPORT[Math.floor(Math.random() * 4) + 1];
			} 
			else {
				$.each(Typewriter.SUPPORT,(index,item) => {
					if (('fadein' + this.$options.effect).toLowerCase() === item.toLowerCase()) {
						effect += item;
						return false;
					}
				});
			}
			if(words[count] == '&'){
				this.$el.append('<br>');
			}else if(words[count] == '#'){
				this.$el.append('   ');
			}else{
				this.$el.append('<span style="display:inline-block;" class="tip '+ effect+'">' + words[count] + '</span>');
			}
			(typeof this.$options.excute === 'function') && this.$options.excute.call(this.$el[0],timer);
			this.$el.trigger('excute',[timer]);
			if (++count >= words.length) {
				clearInterval(timer);
				timer=null;
				(typeof this.$options.complete === 'function') && this.$options.complete.call(this.$el[0]);
				this.$el.trigger('complete');
			}
		}, this.$options.speed);
	}
	Typewriter.prototype.stop = function() {
		console.log('我被停止了');
		clearInterval(timer);
	}
	Typewriter.prototype.clear = function() {
		console.log('前面的家伙被清除了');
		clearInterval(timer);
		this.$el.html('');
	}
	Typewriter.prototype.init = function() {
//		console.log('初始化中...');
		setTimeout(() => {this.addEffect();},this.$options.delay);
	}
	let init = function(el,options) {
//		console.log(el)
//		console.log(options)
		let $el = $(el),
			typewriter = $el.data('typewriter');
		if (typeof options === 'string') {
			// 若存在同名方法，则调用，否则忽略
			typewriter && [options] && typewriter[options]();
		}
		else {
			$el.data('typewriter',(typewriter = new Typewriter(el,typeof options === 'object'  && options )));
			typewriter.init();  // 注册插件
		}
	}
	$.fn.extend({
		typewriter: function(options) {
			return this.each((index,item) => init(item,options));
		}
	})
	return {
		init: init,
	}
})();

//window["typewriter"] = typewriter ;
