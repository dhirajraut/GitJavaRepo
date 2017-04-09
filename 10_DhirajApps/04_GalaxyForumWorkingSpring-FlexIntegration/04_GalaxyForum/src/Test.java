import com.galaxy.forum.domain.mockdata.MockDataHelper;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("UserList - ");
		for (int i = 0; i < 5; i++) {
			//System.out.println(MockDataHelper.getUser());
		}
		System.out.println("\n\nDiscussionThreadList - ");
		for (int i = 0; i < 5; i++) {
			System.out.println(MockDataHelper.getDiscussionThread());
		}
		System.out.println("\n\nMessageList - ");
		for (int i = 0; i < 5; i++) {
			System.out.println(MockDataHelper.getMessage());
		}
	}

}
