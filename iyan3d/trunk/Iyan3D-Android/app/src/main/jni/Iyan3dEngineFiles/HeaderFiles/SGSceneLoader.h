//
//  SGSceneLoader.h
//  Iyan3D
//
//  Created by Karthik on 26/12/15.
//  Copyright © 2015 Smackall Games. All rights reserved.
//

#ifndef SGSceneLoader_h
#define SGSceneLoader_h

#include "SGAction.h"
#include "SGNode.h"
#include "Constants.h"

class SGSceneLoader
{
private:
    SceneManager* smgr;
public:
    SGSceneLoader(SceneManager* smgr, void *scene);
    ~SGSceneLoader();
    
    bool loadSceneData(std::string *filePath);
    bool readScene(ifstream *filePointer);
    void readSceneGlobalInfo(ifstream *filePointer, int& nodeCount);
    void restoreTexture(SGNode* meshObject,int actionType);
    bool removeObject(u16 nodeIndex,bool deAllocScene = false);
    void removeNodeFromInstances(SGNode* sgNode);
    void copyMeshFromOriginalNode(SGNode* sgNode);
    bool removeSelectedObjects();
    bool removeTempNodeIfExists();
    
    void initEnvelope(std::map<int, SGNode*>& envelopes, int jointId);
    
    #ifdef ANDROID
        bool loadSceneData(std::string *filePath, JNIEnv *env, jclass type);
        bool readScene(ifstream *filePointer, JNIEnv *env, jclass type);
    #endif
    
    SGNode* loadNode(NODE_TYPE type,int assetId,string textureName ,std::wstring imagePath = L" ",int imgWidth = 0,int imgHeight = 0,int actionType = OPEN_SAVED_FILE, Vector4 textColor = Vector4(0),string fontFilePath = "",bool isTempNode = false);
    bool loadNode(SGNode *sgNode,int actionType,bool isTempNode = false);
    bool loadNodeOnUndoORedo(SGAction action, int actionType);
    
    void setJointsScale(SGNode *sgNode);
    void addLight(SGNode *light);
    void performUndoRedoOnNodeLoad(SGNode* meshObject,int actionType);
    
    // Instanced Nodes
    
    void createInstance(SGNode* sgNode, NODE_TYPE nType, ActionType actionType);
    bool loadInstance(SGNode* iNode, int origActionId, ActionType actionType);
};

#endif /* SGSceneLoader_h */
