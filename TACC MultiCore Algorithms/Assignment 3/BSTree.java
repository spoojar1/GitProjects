interface BSTree {
	public boolean insert(long key, int thread_id);
	public boolean delete(long key, int thread_id);
	public boolean search(long key, int thread_id);
	public void traverse(int thread_id);
	public void inorder(Node node);
}