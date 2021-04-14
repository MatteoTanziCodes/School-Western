/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matte
 */
public class BinarySearchTree implements BinarySearchTreeADT {

    private BinaryNode r; // declare global variable binary node

    /**
     * Constructor creates a new binary node (root)
     */
    public BinarySearchTree() {

        this.r = new BinaryNode();

    }
    
    /**
     * input: root and key
     * @param r
     * @param key
     * @return - take the key and return the data of the desired binary node
     */
    @Override
    public Pixel get(BinaryNode r, Location key) {

        if (r.isLeaf()) {
            return r.getData();
        } else {
            switch (r.getData().getLocation().compareTo(key)) {
                case 0:
                    return r.getData();
                case -1:
                    return get(r.getRight(), key);
                default:
                    return get(r.getLeft(), key);
            }
        }

    }

    /**
     * 
     * @param r
     * @param data
     * @throws DuplicatedKeyException 
     * 
     * input: root and key
     * output: insert the node into the appropriate place in the tree
     * 
     */
    @Override
    public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
        
        BinaryNode node = findBinaryNode(r, data.getLocation());
        
        if (node.getData() != null) {
            throw new DuplicatedKeyException();
        } else {

            BinaryNode leftChild = new BinaryNode();
            BinaryNode rightChild = new BinaryNode();

            if (node.isLeaf()) {

                node.setData(data);
                node.setLeft(leftChild);
                node.setRight(rightChild);

                leftChild.setParent(node);
                rightChild.setParent(node);

            } else if (node.getData().getLocation().compareTo(data.getLocation()) == 1) {
                put(node.getLeft(), data);
            } else {
                put(node.getRight(), data);
            }
        }

    }
    
    /**
     * 
     * @param r
     * @param key
     * @throws InexistentKeyException 
     * 
     * input: root and key
     * output: remove the node into the appropriate place in the tree
     * 
     */
    @Override
    public void remove(BinaryNode r, Location key) throws InexistentKeyException {

        BinaryNode node = findBinaryNode(r, key);

        if (node.isLeaf()) {
            throw new InexistentKeyException();
        } else {
            if (node.getLeft().isLeaf() && node.getRight().isLeaf()) {

                node.setData(null);

            } else if (node.getLeft().isLeaf() || node.getRight().isLeaf()) {
                BinaryNode parent = node.getParent();
                BinaryNode child;
                if (node.getLeft().isLeaf()) {
                    child = node.getRight();
                } else {
                    child = node.getLeft();
                }
                if (node == getRoot()) {

                    child.setParent(null);
                    r.setRight(null);
                    r.setLeft(null);
                } else {
                    if (parent.getRight() == node) {
                        child.setParent(parent);
                        parent.setRight(child);
                        node.setLeft(null);
                        node.setRight(null);
                    } else {
                        child.setParent(parent);
                        parent.setLeft(child);
                        node.setLeft(null);
                        node.setRight(null);
                    }
                }
            } else {
                BinaryNode smallest = smallestNode(node.getRight());
                node.setData(smallest.getData());
                remove(smallest, smallest.getData().getLocation());
            }

        }

    }

    /**
     * 
     * @param r
     * @param key
     * @return
     * 
     * input: root and key
     * output: find the successor of the node
     */
    @Override
    public Pixel successor(BinaryNode r, Location key) {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = findBinaryNode(r, key);
            if (!node.isLeaf() && !node.getRight().isLeaf()) {
                return smallest(node.getRight());
            } else {
                BinaryNode parent = node.getParent();
                while (node != r && parent.getRight() == node) {
                    node = parent;
                    parent = node.getParent();
                }
                if (node == r) {
                    return null;
                } else {
                    return parent.getData();
                }
            }
        }

    }
    /**
     * 
     * @param r
     * @param key
     * @return
     * 
     * input: root and key
     * output: find the predecessor of the node
     */
    @Override
    public Pixel predecessor(BinaryNode r, Location key) {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = findBinaryNode(r, key);
            if (!node.isLeaf() && !node.getLeft().isLeaf()) {
                return largest(node.getLeft());
            } else {
                BinaryNode parent = node.getParent();
                while (node != r && parent.getLeft() == node) {
                    node = parent;
                    parent = node.getParent();
                }
                if (node == r) {
                    return null;
                } else {
                    return parent.getData();
                }
            }
        }

    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the data of the smallest node
     */
    @Override
    public Pixel smallest(BinaryNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = r;
            while (!node.isLeaf()) {
                node = node.getLeft();
            }
            return node.getParent().getData();
        }

    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the smallest node
     */
    private BinaryNode smallestNode(BinaryNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = r;
            while (!node.isLeaf()) {
                node = node.getLeft();
            }
            return node.getParent();
        }
    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the data of the largest node
     */
    @Override
    public Pixel largest(BinaryNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = r;
            while (!node.isLeaf()) {
                node = node.getRight();
            }
            return node.getParent().getData();
        }
    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the largest node
     */
    public BinaryNode largestNode(BinaryNode r) throws EmptyTreeException {

        if (r.isLeaf()) {
            return null;
        } else {
            BinaryNode node = r;
            while (!node.isLeaf()) {
                node = node.getRight();
            }
            return node.getParent();
        }
    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the root
     */
    @Override
    public BinaryNode getRoot() {

        while (this.r.getParent() != null) {
            this.r = this.r.getParent();
        }
        return this.r;

    }

    /**
     * 
     * @param r
     * @return
     * @throws EmptyTreeException 
     * 
     * input: root and key
     * output: the node of the inserted key
     */
    private BinaryNode findBinaryNode(BinaryNode r, Location key) throws InexistentKeyException {

        if (r.isLeaf()) {
            return r;
        } else {
            switch (r.getData().getLocation().compareTo(key)) {
                case 0:
                    return r;
                case -1:
                    return findBinaryNode(r.getRight(), key);
                default:
                    return findBinaryNode(r.getLeft(), key);
            }

        }

    }
}
