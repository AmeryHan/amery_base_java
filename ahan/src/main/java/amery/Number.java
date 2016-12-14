package amery;


public class Number {

	
	public static void main(String[] main){
		
//		String str1 = "22222";
//		String str2 = "1.1111111111111111111E+12";
//		String str3 = "2222222222222222222222222222222111111111111111111111111111111";
//		Long l1 = Math.round(Double.parseDouble(str1));
//		Long l2 = Math.round(Double.parseDouble(str2));
//		Long l3 = Math.round(Double.parseDouble(str3));
//		System.out.println(l1);
//		System.out.println(l2);
//		System.out.println(l3);
		
		//Long w = 9223372036854775808L;
		
		for(int j = 1 ; j <= 6 ; j ++) {
			Number n = new Number();
			Page p = n.GetStartAndEndForPagination(2, j, 8);
			for(int i = p.getStart(); i <= p.getEnd(); i ++) {
				System.out.print(i + " ");
			}
			System.out.println("----------------当前页"+j);
		}
		/*
		Number n = new Number();
		Page p = n.GetStartAndEndForPagination(6, 2, 7);
		for(int i = p.getStart(); i <= p.getEnd(); i ++) {
			System.out.print(i + " ");
		}*/
	}
	
	//@Test
	public void test2(){
		
	}
	
	public Page GetStartAndEndForPagination(int pageCount, int current_page, int num_display_entries){
		
		Page page = new Page();
		int start = 0 ;
		int end = 0 ;

		if(current_page == 1){
			page.setStart(1);
			page.setEnd(num_display_entries < pageCount ? num_display_entries : pageCount);
			return page;
		}
		if (current_page == pageCount) {
			if(pageCount >= num_display_entries){
				page.setEnd(pageCount);
				page.setStart(pageCount - num_display_entries + 1);				
			}else{
				page.setStart(1);
				page.setEnd(pageCount);
			}

			return page;
		}
		if (current_page > 1 && current_page < pageCount){
			int temp = num_display_entries/2;
			end = current_page + temp > pageCount ? pageCount : current_page + temp ;
			start = end - num_display_entries + 1 ;
			if(start <= 0){
				start = 1 ;
				end = num_display_entries < pageCount ? num_display_entries : pageCount;
			}
			page.setStart(start);
			page.setEnd(end);
			return page;
		}
		if(current_page > pageCount){
			page.setEnd(pageCount);
			start = end - num_display_entries + 1 ;
			if(start <=0){
				start = 1 ;
			}
			page.setStart(start);
			return page;
		}
		return null;
	}
	private int getPageCount(int recordCount,int pageSize) {
		int pageCount ;
		if((recordCount/pageSize)==0)
			pageCount = 1;
		else if((recordCount%pageSize)!=0)
			pageCount = recordCount/pageSize + 1;
		else
			pageCount = recordCount/pageSize;
		
		return pageCount;
	}
	
	class Page {
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		private int start;
		private int end;
	}
	
}
