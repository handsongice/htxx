'use strict';


//点击导入按钮，弹出对话框
$('#importExcel').on('click', function() {
    layer.open({
        title: '请选择需要导入的文件',
        type: 2,
        content: 'hahahah',
        area: ['100%', '100%'],
        shadeClose: true,

    });
});
// //选完文件后不自动上传
// upload.render({
//     elem: '#test8'
//     ,url: '/upload/'
//     ,auto: false
//     //,multiple: true
//     ,bindAction: '#test9'
//     ,done: function(res){
//         console.log(res)
//     }
// });

if(!Array.prototype.map) {
	Array.prototype.map = function(callback, args) {
		var arg, arr, index;

		if(this == null) {
			throw new TypeError('this is null or not defined');
		}

		var obj = new Object(this);
		var len = obj >>> 0;

		if(Object.prototype.toString.call(callback) != '[object Function]') {
			throw new TypeError(callback + 'is not a function');
		}

		if(args) {
			arg = args;
		}
		arr = new Array(len);
		index = 0;

		while(index < len) {
			var kValue, mappedValue;
			if(index in obj) {
				kValue = obj[index];
				mappedValue = callback.call(arg, kValue, index, obj);
				arr[index] = mappedValue;
			}
			index++;
		}
		return arr;
	}
}

if(!Array.indexOf) {  
	Array.prototype.indexOf = function(obj) {    
		for(var i = 0; i < this.length; i++) {      
			if(this[i] == obj) {        
				return i;      
			}
		}    
		return -1;  
	}
}

$('.search-form .search-condition-box li').on('click', function() {
	$(this).addClass('active').siblings().removeClass('active');
});

var urlList = ['home'];
$('#left-menu a[url]').on('click', function() {
	if($(this).siblings().length != 0) return;
	var $a = $(this);
	var url = $a.attr('url');
	tabMenu.addPage(url, $a.text());

});

$('#tabs-list').on('click', 'li[url]', function() {
	tabMenu.showIframe($(this).attr('url'));
});
$('#tabs-list').on('click', '.layui-tab-close', function() {
	tabMenu.removePage($(this).parent('li').attr('url'));
});

$('#prev-tab').on('click', function() {
	tabMenu.tabLocated('first');
});
$('#next-tab').on('click', function() {
	tabMenu.tabLocated('last');
});

var tabMenu = {
	getPageUrl: function(url) {
		return url ;
	},
	/**
	 * 	@index: {num}  定位标签的索引值 ，例外：'first', 'last'
	 */
	tabLocated: function(index) {
		if(index === 'first') {
			$('#tabs-list').css('left', 0);
			return;
		}
		var contWidth = $('#tabs-list').width();
		if(index === 'last') {
			var tabsWidth = $('#tabs-list')[0].scrollWidth;
		} else {
			var tabsWidth = 0;
			for(var i = 0; i < index + 1; i++) {
				tabsWidth += $('#tabs-list li:eq(' + i + ')').outerWidth();
			}
		}
		$('#tabs-list').css('left', (contWidth < tabsWidth) ? (contWidth - tabsWidth) : 0);
	},
	showPage: function(url, pageType) {
		$('#tabs-list li[url="' + url + '"]').addClass('layui-this').siblings('li').removeClass('layui-this');
		tabMenu.showIframe(url);
	},
	showIframe: function(url, pageType) {
		tabMenu.tabLocated(urlList.indexOf(url));
		$('.main-container[src="' + tabMenu.getPageUrl(url) + '"]').addClass('show').siblings().removeClass('show');
	},
	addPage: function(url, name, pageType) {
		if(urlList.indexOf(url) != -1) { //已打开页面
			tabMenu.showPage(url);
			return;
		}
		urlList.push(url);
		$('#tabs-list li.layui-this', parent.document).removeClass('layui-this');
		$('#tabs-list').append('<li url="' + url + '" class="layui-this">' + name + '<i class="layui-icon layui-unselect layui-tab-close">ဆ</i>');
		$('.main-container.show').removeClass('show');
		$('#main').append('<iframe class="show main-container" src="' + tabMenu.getPageUrl(url) + '" frameborder="no" border="0"></iframe>');
	},
	removePage: function(url, pageType) {
		urlList.splice(urlList.indexOf(url), 1);
		var $closeTab = $('#tabs-list li[url="' + url + '"]');
		if($closeTab.hasClass('layui-this')) {
			var showUrl = $closeTab.prev('li').attr('url');
			$closeTab.prev('li').addClass('layui-this');
			$('.main-container[src="' + tabMenu.getPageUrl(showUrl) + '"]').addClass('show')
		}
		$('#tabs-list li[url="' + url + '"]').remove();
		$('.main-container[src="' + tabMenu.getPageUrl(url) + '"]').remove();
	}
};

var dom = function(domStr, pageType) {
	return(pageType === 'children') ? $(domStr, parent.document) : $(domStr);
}
var nemo = {

};