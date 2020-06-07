import java.util.*;

public class CrawlerTask implements Runnable {
    
	// Объект-пара
    private URLDepthPair element;
    
	// Объект со всеми списками
    private URLPool myPool;
    
    /** Конструктор для установки пула переменных URL для пула, переданного методу*/    
	public CrawlerTask(URLPool pool) {
        this.myPool = pool;
    }
    
    /** Запуск заданий CrawlerTasks */
    public void run() {

        element = myPool.get();
        
        // Глубина текущего элемента
        int myDepth = element.getDepth();
        
        LinkedList<URLDepthPair> linksList = new LinkedList<URLDepthPair>();
        linksList = Crawler.parsePage(element);
        
		Crawler.showResults(element, linksList);
		
		for (URLDepthPair pair: linksList) {
			myPool.put(pair);
		}
		
    }
}
