if($(".data-refinement")){
	$all(".data-refinement .switch").forEach((switchEl)=>{
	  switchEl.addEventListener("click",(event)=>{
		
	    const contentEl = switchEl.nextElementSibling;
		
		contentEl.classList.toggle("open");
		
		$all(".data-refinement .switch").forEach((el)=>{
		  const content = el.nextElementSibling;
		  
		  if(content !== contentEl){
			content.classList.remove("open")
		  };
		  if(content.classList.contains("open")){
	        content.style.display = "block";	    
		  } else {
			content.style.display = "none";
		  };
	    });
	  });
	});
	
	$all(".data-refinement .cancel").forEach((switchEl)=>{
	  
	  switchEl.addEventListener("click",()=>{
		
		$all(".data-refinement .switch").forEach((el)=>{
		  const content = el.nextElementSibling;
		  content.classList.remove("open");
		  content.style.display = "none";
	    });
		
	  });
	});
	
	tagManage();
	tagSwitch();
}

if($(".memo-menu")){
	$all(".memo-menu .menu-switch").forEach((switchEl)=>{
	  switchEl.addEventListener("click",(event)=>{
		
	    const contentEl = switchEl.nextElementSibling;
		
		contentEl.classList.toggle("open");
		
		$all(".memo-menu .menu-switch").forEach((el)=>{
		  const content = el.nextElementSibling;
		  
		  if(content !== contentEl){
			content.classList.remove("open")
		  };
		  if(content.classList.contains("open")){
	        content.style.display = "block";	    
		  } else {
			content.style.display = "none";
		  };
	    });
	  });
	});
	
	$all(".menu-box .delete-switch").forEach((switchEl)=>{
	  switchEl.addEventListener("click",(event)=>{
		
	    const contentEl = $(".delete-dialog" ,switchEl.closest(".memo-menu"))
		
		contentEl.classList.toggle("open");
		if(contentEl.classList.contains("open")) $(".overlay").style.display = "block";
		
		$all(".delete-dialog").forEach((el)=>{
		  
		  if(el !== contentEl){
			el.classList.remove("open")
		  };
		  if(el.classList.contains("open")){
	        el.style.display = "block";	    
		  } else {
			el.style.display = "none";
		  };
	    });
	  });
	});
	
	$all(".delete-dialog .delete-cancel").forEach((switchEl)=>{
	  switchEl.addEventListener("click",(event)=>{
		
		$all(".delete-dialog").forEach((el)=>{
		  el.classList.remove("open");
		  el.style.display = "none";
		})
		$(".overlay").style.display = "none";
	   
	  });
	});
}

if($(".form-style")){
	tagManage();
	tagSwitch();
};
function tagSwitch(){
	const switchEl= $(".tag-switch")
	  switchEl.addEventListener("click",()=>{
	    const contentEl = $(".tags")		
		contentEl.classList.toggle("open");
	    if(contentEl.classList.contains("open")){
          contentEl.style.display = "block";	    
	    } else {
		  contentEl.style.display = "none";
	    };
	  });		   
};
function tagManage(){
	const tagCheckBoxes = $all('input[name="tagIds"]');
	const selectedTags = $(".selected-tags");
	
	function updatedSlectedTags(){
		selectedTags.innerHTML = '<span class="tag-switch">▼</span>';
		[...tagCheckBoxes]
		.filter(checkbox => checkbox.checked)
		.forEach(checkbox => {
		  const htmlTagCheckedBox = `
		    <li class="selected-tag">
				<span class="tag-cancel">×</span>
				<span class="selected-tag-name">${checkbox.dataset.tagName}</span>	    
			</li>
		  `;
		  const tagCheckedBox = htmlStrToSingleElement(htmlTagCheckedBox);
		  const tagCancel =  $(".tag-cancel", tagCheckedBox);
		  tagCancel.addEventListener("click", ()=>{
			checkbox.checked = false;
			updatedSlectedTags();
		  });
		  selectedTags.append(tagCheckedBox);
		});
	};
			
	tagCheckBoxes.forEach(checkBox => {
		checkBox.addEventListener("change",updatedSlectedTags);
	});
	updatedSlectedTags();

};
function $(selectorText, parentElement = document){
    const el = parentElement?.querySelector(selectorText);
    return el;
};
function $all(selectorText, parentElement = document){
    const els = parentElement?.querySelectorAll(selectorText);
    return els;
};
function safeText(text, selectorText, parentElement = document){
    const el = $(selectorText, parentElement);
    if(el) el.textContent = text ?? "";
};
function htmlStrToSingleElement(htmlStr){
    const dummyDiv = document.createElement("div");
    dummyDiv.innerHTML = htmlStr;
    return dummyDiv.firstElementChild;
};